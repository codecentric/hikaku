package de.codecentric.hikaku.converters.openapi.extractors

import de.codecentric.hikaku.converters.openapi.extensions.referencedSchema
import de.codecentric.hikaku.endpoints.QueryParameter
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.parameters.Parameter as OpenApiParameter
import io.swagger.v3.oas.models.parameters.QueryParameter as OpenApiQueryParameter

internal class QueryParameterExtractor(private val openApi: OpenAPI) {

    operator fun invoke(parameters: List<OpenApiParameter>?): Set<QueryParameter> {
        return extractInlineQueryParameters(parameters).union(extractQueryParametersFromComponents(parameters))
    }

    private fun extractInlineQueryParameters(parameters: List<OpenApiParameter>?): Set<QueryParameter> {
        return parameters
                ?.filterIsInstance<OpenApiQueryParameter>()
                ?.map { QueryParameter(it.name, it.required) }
                .orEmpty()
                .toSet()
    }

    private fun extractQueryParametersFromComponents(parameters: List<OpenApiParameter>?): Set<QueryParameter> {
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
                ?.filter { it?.`in` == "query" }
                ?.map { QueryParameter(it?.name ?: "", it?.required ?: false) }
                .orEmpty()
                .toSet()
    }
}