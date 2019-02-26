package de.codecentric.hikaku.converters

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.endpoints.Endpoint

/**
 * Converts either a specific type of specification or implementation into the internal hikaku format in order to be able to perform a matching on the extracted components.
 */
interface EndpointConverter {

    /** Result of the conversion containing all extracted [Endpoint]s. */
    val conversionResult: Set<Endpoint>

    /** List of [Feature]s that this [EndpointConverter]s supports. */
    val supportedFeatures: SupportedFeatures
}