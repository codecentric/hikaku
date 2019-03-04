package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.*
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.EndpointConverterException
import de.codecentric.hikaku.converters.raml.extensions.*
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.extensions.checkFileValidity
import org.raml.v2.api.RamlModelBuilder
import org.raml.v2.api.RamlModelResult
import org.raml.v2.api.model.v10.resources.Resource
import java.io.File
import java.nio.file.Path

class RamlConverter(private val ramlSpecification: File) : AbstractEndpointConverter()  {

    constructor(ramlSpecification: Path) : this(ramlSpecification.toFile())

    override val supportedFeatures = SupportedFeatures(
            Feature.QueryParameter,
            Feature.PathParameter,
            Feature.HeaderParameter
    )

    override fun convert(): Set<Endpoint> {
        val ramlParserResult: RamlModelResult?

        try {
            ramlSpecification.checkFileValidity(".raml")
            ramlParserResult = RamlModelBuilder().buildApi(ramlSpecification)
        } catch(throwable: Throwable) {
            throw EndpointConverterException(throwable)
        }

        if (ramlParserResult.isVersion08) {
            throw EndpointConverterException("Unsupported RAML version")
        }

        if (ramlParserResult.hasErrors()) {
            throw EndpointConverterException(ramlParserResult.validationResults.joinToString("\n"))
        }

        return ramlParserResult.apiV10?.resources()?.let { resourceList ->
            resourceList.flatMap { findEndpoints(it) }
        }
        .orEmpty()
        .toSet()
    }

    private fun findEndpoints(resource: Resource): List<Endpoint> {
        val endpoints = mutableListOf<Endpoint>()

        if (resource.resources().isNotEmpty()) {
            endpoints += resource.resources().flatMap {
                return@flatMap findEndpoints(it)
            }
        }

        if (resource.methods().isNotEmpty()) {
            endpoints += resource.methods().map {
                    Endpoint(
                            path = resource.resourcePath(),
                            httpMethod = it.hikakuHttpMethod(),
                            queryParameters = it.hikakuQueryParameters(),
                            pathParameters = it.resource()?.hikakuPathParameters().orEmpty(),
                            headerParameters = it?.hikakuHeaderParameters().orEmpty(),
                            consumes = it.requestMediaTypes(),
                            produces = it.responseMediaTypes()
                    )
            }
        }

        return endpoints
    }
}