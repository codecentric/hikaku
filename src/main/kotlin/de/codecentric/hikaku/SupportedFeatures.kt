package de.codecentric.hikaku

class SupportedFeatures(
    private val supportedFeatures: Set<Feature> = emptySet()
) : Set<SupportedFeatures.Feature> by supportedFeatures {

    constructor(vararg feature: Feature): this(feature.toSet())

    enum class Feature {
        QueryParameterName,
        QueryParameterRequired,
        PathParameter,
        HeaderParameterName,
        HeaderParameterRequired
    }
}