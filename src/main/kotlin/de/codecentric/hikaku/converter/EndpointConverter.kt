package de.codecentric.hikaku.converter

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.endpoints.Endpoint

interface EndpointConverter {

    val conversionResult: Set<Endpoint>

    val supportedFeatures: SupportedFeatures
}