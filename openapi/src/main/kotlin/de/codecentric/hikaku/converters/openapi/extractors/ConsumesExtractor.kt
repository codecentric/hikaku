package de.codecentric.hikaku.converters.openapi.extractors

import de.codecentric.hikaku.converters.openapi.extensions.referencedSchema
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation

internal class ConsumesExtractor(private val openApi: OpenAPI) {

    operator fun invoke(operation: Operation?): Set<String> {
        return operation?.requestBody
                ?.content
                ?.keys
                .orEmpty()
                .union(extractConsumesFromComponents(operation))
                .toSet()
    }

    private fun extractConsumesFromComponents(operation: Operation?): Set<String> {
        return operation?.requestBody
                ?.referencedSchema
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