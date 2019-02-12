package de.codecentric.hikaku.reporter

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.endpoints.Endpoint

data class MatchResult(
        val supportedFeatures: SupportedFeatures,
        val specificationEndpoints: Set<Endpoint>,
        val implementationEndpoints: Set<Endpoint>,
        val notFound: Set<Endpoint>,
        val notExpected: Set<Endpoint>
)