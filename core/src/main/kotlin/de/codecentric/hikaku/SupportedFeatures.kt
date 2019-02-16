package de.codecentric.hikaku

import de.codecentric.hikaku.converter.EndpointConverter

/**
 * A list of features supported by an [EndpointConverter]
 */
class SupportedFeatures(
    private val supportedFeatures: Set<Feature> = emptySet()
) : Set<SupportedFeatures.Feature> by supportedFeatures {

    constructor(vararg feature: Feature): this(feature.toSet())

    enum class Feature {
        /** Checks the equality of the names of query parameters. */
        QueryParameter,
        /** Checks the equality of the names of path parameters. */
        PathParameter,
        /** Checks the equality of the names of header parameters. */
        HeaderParameter,
        /** Checks supported media type of responses. */
        Produces,
        /** Checks supported media type of requests. */
        Consumes
    }
}