package de.codecentric.hikaku.matcher

import de.codecentric.hikaku.SupportedFeatures.Feature.PathParameter
import de.codecentric.hikaku.endpoints.Endpoint

object PathParameterMatcher {

    fun matchPathParameter(specificationEndpoint: Endpoint, implementationEndpoint: Endpoint): List<MatchResult<*>> {
        if (specificationEndpoint.pathParameter.size != implementationEndpoint.pathParameter.size) {
            return listOf(
                    MatchResult(
                            relatesTo = "Number of path parameters for [${specificationEndpoint.httpMethod} ${implementationEndpoint.path}]",
                            specificationValue = specificationEndpoint.pathParameter.size,
                            implementationValue = implementationEndpoint.pathParameter.size
                    )
            )
        }

        val pathParameterInSpecification = specificationEndpoint.pathParameter
                .map { it.parameterName }
                .sorted()

        val pathParameterInImplementation = implementationEndpoint.pathParameter
                .map { it.parameterName }
                .sorted()

        return pathParameterInSpecification.zip(pathParameterInImplementation)
                .map { (sp, ip) ->
                    MatchResult(PathParameter.toString(), sp, ip)
                }
    }
}