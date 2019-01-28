package de.codecentric.hikaku

import de.codecentric.hikaku.SupportedFeatures.Feature.*
import de.codecentric.hikaku.converter.EndpointConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.HEAD
import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS
import de.codecentric.hikaku.matcher.EndpointMatchResult
import de.codecentric.hikaku.matcher.MatchResult
import de.codecentric.hikaku.matcher.MatchResultGroup
import de.codecentric.hikaku.matcher.PathParameterMatcher.matchPathParameter
import de.codecentric.hikaku.matcher.PreCheckListSizeMatchResult
import de.codecentric.hikaku.matcher.QueryParameterMatcher.matchQueryParameterName
import de.codecentric.hikaku.matcher.QueryParameterMatcher.matchQueryParameterRequired
import kotlin.test.fail

class Hikaku(
        private val specification: EndpointConverter,
        private val implementation: EndpointConverter,
        var config: HikakuConfig = HikakuConfig()
) {
    private val supportedFeatures = specification.supportedFeatures.intersect(implementation.supportedFeatures)
    private val matchResults: MutableList<MatchResultGroup> = mutableListOf()

    init {
        if (!javaClass.desiredAssertionStatus()) {
            throw IllegalStateException("Unable to execute Hikaku tests. Always run Hikaku tests with vm-argument: -ea")
        }
    }

    private fun Set<Endpoint>.applyConfig(config: HikakuConfig): List<Endpoint> {
        return this.filterNot { config.ignorePaths.contains(it.path) }
                .filterNot { config.ignoreHttpMethodHead && it.httpMethod == HEAD }
                .filterNot { config.ignoreHttpMethodOptions && it.httpMethod == OPTIONS }
    }

    private fun reportResult() {
        config.reporter.report(matchResults)
    }

    fun match() {
        val specificationEndpoints = specification
                .conversionResult
                .applyConfig(config)
                .sortedWith(compareBy(Endpoint::path, Endpoint::httpMethod))

        val implementationEndpoints = implementation
                .conversionResult
                .applyConfig(config)
                .sortedWith(compareBy(Endpoint::path, Endpoint::httpMethod))

        createMatchResults(specificationEndpoints, implementationEndpoints)

        reportResult()

        if (!isMatch()) {
            fail("Specification does not match implementation.")
        }
    }

    private fun isMatch(): Boolean {
        return matchResults
                .map { it.matches() }
                .fold(true) { lh, rh -> lh && rh }
    }

    private fun createMatchResults(specificationEndpoints: List<Endpoint>, implementationEndpoints: List<Endpoint>) {
        if (specificationEndpoints.size != implementationEndpoints.size) {
            matchResults.add(
                    PreCheckListSizeMatchResult(
                        relatesTo = "Number of endpoints",
                        specificationListSize = specificationEndpoints.size,
                        implementationListSize = implementationEndpoints.size
                    )
            )
            return
        }

        for (index in specificationEndpoints.indices) {
            val specificationEndpoint = specificationEndpoints[index]
            val implementationEndpoint = implementationEndpoints[index]

            val httpMethodResultMatcher = MatchResult(
                    relatesTo = "HTTP method",
                    specificationValue = specificationEndpoint.httpMethod,
                    implementationValue = implementationEndpoint.httpMethod
            )

            val pathResultMatcher = MatchResult(
                    relatesTo = "Path",
                    specificationValue = specificationEndpoint.path,
                    implementationValue = implementationEndpoint.path
            )

            val additionalFeatures = supportedFeatures.flatMap {
                when (it) {
                    QueryParameterName -> matchQueryParameterName(specificationEndpoint, implementationEndpoint)
                    QueryParameterRequired -> matchQueryParameterRequired(specificationEndpoint, implementationEndpoint)
                    PathParameter -> matchPathParameter(specificationEndpoint, implementationEndpoint)
                }
            }

            matchResults.add(
                    EndpointMatchResult(
                            httpMethodResultMatcher,
                            pathResultMatcher,
                            additionalFeatures
                    )
            )
        }
    }
}