package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.raml.extensions.hikakuQueryParameters
import de.codecentric.hikaku.converters.raml.extensions.httpMethod
import de.codecentric.hikaku.converters.raml.extensions.pathParameters
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.extensions.checkFileValidity
import org.raml.v2.api.RamlModelBuilder
import org.raml.v2.api.model.v10.resources.Resource
import java.io.File
import java.nio.file.Path

class RamlConverter private constructor(private val ramlSpecification: File) : AbstractEndpointConverter()  {

    init {
        ramlSpecification.checkFileValidity(".raml")
    }

    override val supportedFeatures = SupportedFeatures()

    override fun convert(): Set<Endpoint> {
        val ramlParserResult = RamlModelBuilder().buildApi(ramlSpecification)

        if (ramlParserResult.isVersion08) {
            throw IllegalArgumentException("Unsupported RAML version")
        }

        if (ramlParserResult.hasErrors()) {
            throw IllegalArgumentException(ramlParserResult.validationResults.joinToString("\n"))
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
                            httpMethod = it.httpMethod(),
                            queryParameters = it.hikakuQueryParameters(),
                            pathParameters = it.resource()?.pathParameters().orEmpty()
                    )
            }
        }

        return endpoints
    }

    companion object {
        @JvmStatic
        @JvmName("usingPath")
        operator fun invoke(ramlSpecification: Path): RamlConverter {
            return RamlConverter(ramlSpecification.toFile())
        }

        @JvmStatic
        @JvmName("usingFile")
        operator fun invoke(ramlSpecification: File): RamlConverter {
            return RamlConverter(ramlSpecification)
        }
    }
}