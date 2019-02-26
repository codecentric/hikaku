package de.codecentric.hikaku.reporters

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.endpoints.Endpoint

/**
 * Contains the complete result.
 * @param supportedFeatures Contains all features which have been used for the match.
 * @param specificationEndpoints All [Endpoint]s extracted from the specification.
 * @param implementationEndpoints All [Endpoint]s extracted from the implementation.
 * @param notFound A [Set] of [Endpoint]s which were expected due to their existence in the specification, but which couldn't be found.
 * @param notExpected A [Set] of [Endpoint]s which have been found in the implementation, but which were unexpected, because they don't exist in the specification.
 */
data class MatchResult(
        val supportedFeatures: SupportedFeatures,
        val specificationEndpoints: Set<Endpoint>,
        val implementationEndpoints: Set<Endpoint>,
        val notFound: Set<Endpoint>,
        val notExpected: Set<Endpoint>
)