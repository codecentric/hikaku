package de.codecentric.hikaku.converters.openapi.extractors

import de.codecentric.hikaku.converters.openapi.extensions.referencedSchema
import de.codecentric.hikaku.endpoints.PathParameter
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.parameters.PathParameter as OpenApiPathParameter
import io.swagger.v3.oas.models.parameters.Parameter as OpenApiParameter

internal class PathParameterExtractor(private val openApi: OpenAPI) {

    operator fun invoke(operation: Operation?): Set<PathParameter> {
        return extractInlinePathParameters(operation).union(extractPathParametersFromComponents(operation))
    }

    private fun extractInlinePathParameters(operation: Operation?): Set<PathParameter> {
        return operation?.parameters
                ?.filterIsInstance<OpenApiPathParameter>()
                ?.map { PathParameter(it.name) }
                .orEmpty()
                .toSet()
    }

    private fun extractPathParametersFromComponents(operation: Operation?): Set<PathParameter> {
        return operation?.parameters
                ?.filterIsInstance<OpenApiParameter>()
                ?.filter { it.referencedSchema != null }
                ?.map {
                    Regex("#/components/parameters/(?<key>.+)")
                            .find(it.referencedSchema)
                            ?.groups
                            ?.get("key")
                            ?.value
                }
                ?.map {
                    openApi.components
                            .parameters[it]
                }
                ?.filter { it?.`in` == "path" }
                ?.map { PathParameter(it?.name ?: "") }
                .orEmpty()
                .toSet()
    }
}