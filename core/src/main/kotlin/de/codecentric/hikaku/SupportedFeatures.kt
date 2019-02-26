package de.codecentric.hikaku

import de.codecentric.hikaku.converters.EndpointConverter

/**
 * A list of features supported by an [EndpointConverter].
 */
class SupportedFeatures(
    private val supportedFeatures: Set<Feature> = emptySet()
) : Set<SupportedFeatures.Feature> by supportedFeatures {

    constructor(vararg feature: Feature): this(feature.toSet())

    enum class Feature {
        /** Checks the equality of query parameters. */
        QueryParameter,
        /** Checks the equality of path parameters. */
        PathParameter,
        /** Checks the equality of header parameters. */
        HeaderParameter,
        /** Checks supported media type of responses. */
        Produces,
        /** Checks supported media type of requests. */
        Consumes
    }
}