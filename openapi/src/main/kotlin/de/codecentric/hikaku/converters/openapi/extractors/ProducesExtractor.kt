package de.codecentric.hikaku.converters.openapi.extractors

import de.codecentric.hikaku.converters.openapi.extensions.referencedSchema
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation

internal class ProducesExtractor(private val openApi: OpenAPI) {

    operator fun invoke(operation: Operation?): Set<String> {
        return operation?.responses
                ?.flatMap {
                    it.value
                            ?.content
                            ?.keys
                            .orEmpty()
                }
                .orEmpty()
                .union(extractResponsesFromComponents(operation))
                .toSet()
    }

    private fun extractResponsesFromComponents(operation: Operation?): Set<String> {
        return operation?.responses
                ?.mapNotNull { it.value.referencedSchema }
                ?.map {
                    Regex("#/components/responses/(?<key>.+)")
                            .find(it)
                            ?.groups
                            ?.get("key")
                            ?.value
                }
                ?.flatMap {
                    openApi.components
                            .responses[it]
                            ?.content
                            ?.keys
                            .orEmpty()
                }
                .orEmpty()
                .toSet()
    }
}