package de.codecentric.hikaku.converters.openapi.extractors

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation

internal class ConsumesExtractor(private val openApi: OpenAPI) {

    fun extractConsumesMediaTypes(operation: Operation?): Set<String> {
        return operation?.requestBody
                ?.content
                ?.keys
                .orEmpty()
                .union(extractConsumesFromComponents(operation))
                .toSet()
    }

    private fun extractConsumesFromComponents(operation: Operation?): Set<String> {
        return operation?.requestBody
                ?.`$ref`
                ?.let {
                    Regex("#/components/requestBodies/(?<key>.+)")
                            .find(it)
                            ?.groups
                            ?.get("key")
                            ?.value
                }
                ?.let {
                    openApi.components
                            .requestBodies[it]
                            ?.content
                            ?.keys
                }
                .orEmpty()
    }
}