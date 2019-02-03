package de.codecentric.hikaku.matcher

import de.codecentric.hikaku.SupportedFeatures.Feature.PathParameter
import de.codecentric.hikaku.endpoints.Endpoint

internal object PathParameterMatcher {

    fun matchPathParameter(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<MatchResult<*>> {
        if (specificationEndpoint.pathParameters.size != implementationEndpoint.pathParameters.size) {
            return listOf(
                    MatchResult(
                            relatesTo = "Number of path parameters for [${specificationEndpoint.httpMethod} ${implementationEndpoint.path}]",
                            specificationValue = specificationEndpoint.pathParameters.size,
                            implementationValue = implementationEndpoint.pathParameters.size
                    )
            )
        }

        val pathParameterInSpecification = specificationEndpoint.pathParameters
                .map { it.parameterName }
                .sorted()

        val pathParameterInImplementation = implementationEndpoint.pathParameters
                .map { it.parameterName }
                .sorted()

        return pathParameterInSpecification.zip(pathParameterInImplementation)
                .map { (sp, ip) ->
                    MatchResult(PathParameter.toString(), sp, ip)
                }
    }
}