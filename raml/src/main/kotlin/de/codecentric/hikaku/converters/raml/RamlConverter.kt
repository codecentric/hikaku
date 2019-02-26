package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.raml.extensions.httpMethod
import de.codecentric.hikaku.endpoints.Endpoint
import org.raml.v2.api.RamlModelBuilder
import org.raml.v2.api.model.v10.resources.Resource
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class RamlConverter private constructor(private val ramlSpecification: File) : AbstractEndpointConverter()  {

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
                            httpMethod = it.httpMethod()
                    )
            }
        }

        return endpoints
    }

    companion object {
        @JvmStatic
        @JvmName("usingPath")
        operator fun invoke(ramlSpecification: Path): RamlConverter {
            checkFileValidity(ramlSpecification)

            return RamlConverter(ramlSpecification.toFile())
        }

        private fun checkFileValidity(ramlSpecification: Path) {
            if (!Files.exists(ramlSpecification)) {
                throw IllegalArgumentException("Given specification file does not exist.")
            }

            if (!Files.isRegularFile(ramlSpecification)) {
                throw IllegalArgumentException("Given specification file is not a regular file.")
            }

            if (!isValidFileExtension(ramlSpecification)) {
                throw IllegalArgumentException("Given file is not of type *.raml.")
            }
        }

        @JvmStatic
        @JvmName("usingFile")
        operator fun invoke(ramlSpecification: File): RamlConverter {
            checkFileValidity(ramlSpecification.toPath())

            return RamlConverter(ramlSpecification)
        }

        private fun isValidFileExtension(ramlSpecification: Path): Boolean {
            val fileName = ramlSpecification.fileName.toString()

            return fileName.endsWith(".raml")
        }
    }
}