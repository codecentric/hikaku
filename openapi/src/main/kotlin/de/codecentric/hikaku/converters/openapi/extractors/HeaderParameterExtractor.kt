package de.codecentric.hikaku.converters.openapi.extractors

import de.codecentric.hikaku.converters.openapi.extensions.referencedSchema
import de.codecentric.hikaku.endpoints.HeaderParameter
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.parameters.Parameter as OpenApiParameter
import io.swagger.v3.oas.models.parameters.HeaderParameter as OpenApiHeaderParameter

internal class HeaderParameterExtractor(private val openApi: OpenAPI) {

    operator fun invoke(parameters: List<OpenApiParameter>?): Set<HeaderParameter> {
        return extractInlineHeaderParameters(parameters).union(extractHeaderParametersFromComponents(parameters))
    }

    private fun extractInlineHeaderParameters(parameters: List<OpenApiParameter>?): Set<HeaderParameter> {
        return parameters
                ?.filterIsInstance<OpenApiHeaderParameter>()
                ?.map { HeaderParameter(it.name, it.required) }
                .orEmpty()
                .toSet()
    }

    private fun extractHeaderParametersFromComponents(parameters: List<OpenApiParameter>?): Set<HeaderParameter> {
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
                ?.filter { it?.`in` == "header" }
                ?.map { HeaderParameter(it?.name ?: "", it?.required ?: false) }
                .orEmpty()
                .toSet()
    }
}