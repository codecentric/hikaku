package de.codecentric.hikaku.converters.openapi.extractors

import de.codecentric.hikaku.endpoints.HeaderParameter
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.parameters.Parameter as OpenApiParameter
import io.swagger.v3.oas.models.parameters.HeaderParameter as OpenApiHeaderParameter

internal class HeaderParameterExtractor(private val openApi: OpenAPI) {

    fun extractHeaderParameters(operation: Operation?): Set<HeaderParameter> {
        return extractInlineHeaderParameters(operation).union(extractHeaderParametersFromComponents(operation))
    }

    private fun extractInlineHeaderParameters(operation: Operation?): Set<HeaderParameter> {
        return operation?.parameters
                ?.filter { it is OpenApiHeaderParameter }
                ?.map { HeaderParameter(it.name, it.required) }
                .orEmpty()
                .toSet()
    }

    private fun extractHeaderParametersFromComponents(operation: Operation?): Set<HeaderParameter> {
        return operation?.parameters
                ?.filter { it is OpenApiParameter }
                ?.filter { it.`$ref` != null }
                ?.map {
                    Regex("#/components/parameters/(?<key>.+)")
                            .find(it.`$ref`)
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