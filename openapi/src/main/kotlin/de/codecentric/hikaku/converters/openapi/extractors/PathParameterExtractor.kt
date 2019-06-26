package de.codecentric.hikaku.converters.openapi.extractors

import de.codecentric.hikaku.converters.openapi.extensions.referencedSchema
import de.codecentric.hikaku.endpoints.PathParameter
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.parameters.PathParameter as OpenApiPathParameter
import io.swagger.v3.oas.models.parameters.Parameter as OpenApiParameter

internal class PathParameterExtractor(private val openApi: OpenAPI) {

    operator fun invoke(parameters: List<OpenApiParameter>?): Set<PathParameter> {
        return extractInlinePathParameters(parameters).union(extractPathParametersFromComponents(parameters))
    }

    private fun extractInlinePathParameters(parameters: List<OpenApiParameter>?): Set<PathParameter> {
        return parameters
                ?.filterIsInstance<OpenApiPathParameter>()
                ?.map { PathParameter(it.name) }
                .orEmpty()
                .toSet()
    }

    private fun extractPathParametersFromComponents(parameters: List<OpenApiParameter>?): Set<PathParameter> {
        return parameters
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