package de.codecentric.hikaku

import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converter.EndpointConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.HEAD
import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS
import de.codecentric.hikaku.reporter.MatchResult
import de.codecentric.hikaku.reporter.Reporter
import kotlin.test.fail

/**
 * Entry point for writing a hikaku test. Provide the [EndpointConverter]s and call [match] to test if the specification and the implementation of your REST-API match.
 * @param specification An [EndpointConverter] which converts your specification for the equality check.
 * @param implementation An [EndpointConverter]  which converts your implementation for the equality check.
 * @param config The configuration is optional. It lets you partially control the matching.
 */
class Hikaku(
        private val specification: EndpointConverter,
        private val implementation: EndpointConverter,
        var config: HikakuConfig = HikakuConfig()
) {
    private val supportedFeatures = SupportedFeatures(specification.supportedFeatures.intersect(implementation.supportedFeatures))

    private fun Set<Endpoint>.applyConfig(config: HikakuConfig): List<Endpoint> {
        return this.filterNot { config.ignorePaths.contains(it.path) }
                .filterNot { config.ignoreHttpMethodHead && it.httpMethod == HEAD }
                .filterNot { config.ignoreHttpMethodOptions && it.httpMethod == OPTIONS }
    }

    private fun reportResult(matchResult: MatchResult) {
        config.reporter.report(matchResult)
    }

    /**
     * Calling this method creates a [MatchResult]. It will be passed to the [Reporter] defined in the configuration and call [assert] with the end result.
     */
    fun match() {
        val specificationEndpoints = specification
                .conversionResult
                .applyConfig(config)
                .toSet()

        val implementationEndpoints = implementation
                .conversionResult
                .applyConfig(config)
                .toSet()

        val notExpected = implementationEndpoints.toMutableSet()
        val notFound = specificationEndpoints.toMutableSet()

        specificationEndpoints.forEach { currentEndpoint ->
            if (iterableContains(notExpected, currentEndpoint)) {
                notExpected.removeIf(endpointMatches(currentEndpoint))
                notFound.removeIf(endpointMatches(currentEndpoint))
            }
        }

        reportResult(
                MatchResult(
                        supportedFeatures,
                        specificationEndpoints,
                        implementationEndpoints,
                        notFound,
                        notExpected
                )
        )

        if (notExpected.isNotEmpty() || notFound.isNotEmpty()) {
            fail("Implementation does not match specification.")
        }
    }

    private fun endpointMatches(otherEndpoint: Endpoint): (Endpoint) -> Boolean {
        return {
            var matches = true
            matches = matches && it.path == otherEndpoint.path
            matches = matches && it.httpMethod == otherEndpoint.httpMethod

            supportedFeatures.forEach { feature ->
                matches = when (feature) {
                    Feature.QueryParameter -> matches && it.queryParameters == otherEndpoint.queryParameters
                    Feature.PathParameter -> matches && it.pathParameters ==  otherEndpoint.pathParameters
                    Feature.HeaderParameter -> matches && it.headerParameters == otherEndpoint.headerParameters
                    Feature.Produces -> matches && it.produces == otherEndpoint.produces
                    Feature.Consumes -> matches && it.consumes == otherEndpoint.consumes
                }
            }

            matches
        }
    }

    private fun iterableContains(notExpected: Set<Endpoint>, value: Endpoint) = notExpected.any(endpointMatches(value))
}