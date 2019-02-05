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
        QueryParameterName,
        /** Checks if a query parameter is required. */
        QueryParameterRequired,
        /** Checks the equality of the names of path parameters. */
        PathParameter,
        /** Checks the equality of the names of header parameters. */
        HeaderParameterName,
        /** Checks if a header parameter is required. */
        HeaderParameterRequired,
        /** Checks the supported media types of the response. */
        Produces
    }
}