package de.codecentric.hikaku.converter

import de.codecentric.hikaku.endpoints.Endpoint

abstract class AbstractEndpointConverter : EndpointConverter {

    override val conversionResult: Set<Endpoint> by lazy {
        this.convert()
    }

    abstract fun convert(): Set<Endpoint>
}