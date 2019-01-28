package de.codecentric.hikaku.matcher

import de.codecentric.hikaku.SupportedFeatures.Feature.*
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.QueryParameter

object QueryParameterMatcher {

    fun matchQueryParameterName(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<MatchResult<*>> {
        if (specificationEndpoint.queryParameter.size != implementationEndpoint.queryParameter.size) {
            return createListSizesDifferMatchResult(specificationEndpoint, implementationEndpoint)
        }

        return createPair(specificationEndpoint, implementationEndpoint).map {
            val specificationParameterName = it.first.parameterName
            val implementationParameterName = it.second.parameterName

            MatchResult(
                    QueryParameterName.toString(),
                    specificationParameterName,
                    implementationParameterName
            )
        }
    }

    fun matchQueryParameterRequired(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<MatchResult<*>> {
        if (specificationEndpoint.queryParameter.size != implementationEndpoint.queryParameter.size) {
            return createListSizesDifferMatchResult(specificationEndpoint, implementationEndpoint)
        }

        return createPair(specificationEndpoint, implementationEndpoint).map {
            val specificationRequiredValue = it.first.required
            val implementationRequiredValue = it.second.required

            MatchResult(
                    QueryParameterRequired.toString(),
                    specificationRequiredValue,
                    implementationRequiredValue
            )
        }
    }

    private fun createPair(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<Pair<QueryParameter, QueryParameter>> {
        return specificationEndpoint.queryParameter
                .sortedBy { it.parameterName }
                .zip(implementationEndpoint.queryParameter.sortedBy { it.parameterName })
    }

    private fun createListSizesDifferMatchResult(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<MatchResult<Int>> {
        return listOf(
                MatchResult(
                        relatesTo = "Number of query parameters for [${specificationEndpoint.httpMethod} ${implementationEndpoint.path}]",
                        specificationValue = specificationEndpoint.queryParameter.size,
                        implementationValue = implementationEndpoint.queryParameter.size
                )
        )
    }
}