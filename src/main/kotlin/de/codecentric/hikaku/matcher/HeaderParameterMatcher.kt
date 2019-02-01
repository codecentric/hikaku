package de.codecentric.hikaku.matcher

import de.codecentric.hikaku.SupportedFeatures.Feature.*
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HeaderParameter

object HeaderParameterMatcher {

    fun matchHeaderParameterName(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<MatchResult<*>> {
        if (specificationEndpoint.headerParameters.size != implementationEndpoint.headerParameters.size) {
            return createListSizesDifferMatchResult(specificationEndpoint, implementationEndpoint)
        }

        return createPair(specificationEndpoint, implementationEndpoint).map {
            val specificationParameterName = it.first.parameterName
            val implementationParameterName = it.second.parameterName

            MatchResult(
                    HeaderParameterName.toString(),
                    specificationParameterName,
                    implementationParameterName
            )
        }
    }

    fun matchHeaderParameterRequired(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<MatchResult<*>> {
        if (specificationEndpoint.headerParameters.size != implementationEndpoint.headerParameters.size) {
            return createListSizesDifferMatchResult(specificationEndpoint, implementationEndpoint)
        }

        return createPair(specificationEndpoint, implementationEndpoint).map {
            val specificationRequiredValue = it.first.required
            val implementationRequiredValue = it.second.required

            MatchResult(
                    HeaderParameterRequired.toString(),
                    specificationRequiredValue,
                    implementationRequiredValue
            )
        }
    }

    private fun createPair(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<Pair<HeaderParameter, HeaderParameter>> {
        val specificationHeaderParameter = specificationEndpoint.headerParameters
                .sortedBy { it.parameterName }

        val implementationHeaderParameter = implementationEndpoint.headerParameters
                .sortedBy { it.parameterName }

        return specificationHeaderParameter.zip(implementationHeaderParameter)
    }

    private fun createListSizesDifferMatchResult(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<MatchResult<Int>> {
        return listOf(
                MatchResult(
                        relatesTo = "Number of header parameters for [${specificationEndpoint.httpMethod} ${implementationEndpoint.path}]",
                        specificationValue = specificationEndpoint.headerParameters.size,
                        implementationValue = implementationEndpoint.headerParameters.size
                )
        )
    }
}