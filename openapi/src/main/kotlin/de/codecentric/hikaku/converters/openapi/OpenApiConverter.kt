package de.codecentric.hikaku.converters.openapi

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.openapi.extensions.httpMethods
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import io.swagger.v3.oas.models.parameters.QueryParameter as OpenApiQueryParameter
import io.swagger.v3.oas.models.parameters.PathParameter as OpenApiPathParameter
import io.swagger.v3.oas.models.parameters.HeaderParameter as OpenApiHeaderParameter
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converters.SpecificationParserException
import de.codecentric.hikaku.converters.openapi.extensions.hikakuHeaderParameters
import de.codecentric.hikaku.converters.openapi.extensions.hikakuPathParameters
import de.codecentric.hikaku.converters.openapi.extensions.hikakuQueryParameters
import de.codecentric.hikaku.extensions.checkFileValidity
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.parser.OpenAPIV3Parser
import java.io.File
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files.*
import java.nio.file.Path

/**
 * Extracts and converts [Endpoint]s from OpenAPI 3.0.X document. Either a *.yaml*, *.yml* or a *.json* file.
 *
 * In Java invoke via: `OpenApiConverter.usingPath(Paths.get("");` or `OpenApiConverter.usingFile(new File("");`
 */
class OpenApiConverter private constructor(private val specificationContent: String) : AbstractEndpointConverter() {

    private lateinit var openApi: OpenAPI

    constructor(openApiSpecification: File): this(openApiSpecification.toPath())
    constructor(openApiSpecification: Path): this(readFileContent(openApiSpecification))

    override val supportedFeatures = SupportedFeatures(
            Feature.QueryParameter,
            Feature.PathParameter,
            Feature.HeaderParameter,
            Feature.Produces,
            Feature.Consumes
    )

    override fun convert(): Set<Endpoint> {
        try {
            return parseOpenApi()
        } catch (throwable: Throwable) {
            throw SpecificationParserException(throwable)
        }
    }

    private fun parseOpenApi(): Set<Endpoint> {
        openApi = OpenAPIV3Parser().readContents(specificationContent, null, null).openAPI

        return openApi.paths.flatMap { (path, pathItem) ->
            pathItem.httpMethods().map { (httpMethod: HttpMethod, operation: Operation?) ->
                createEndpoint(path, httpMethod, operation)
            }
        }
        .toSet()
    }

    private fun createEndpoint(
            path: String,
            httpMethod: HttpMethod,
            operation: Operation?
    ) = Endpoint(
            path = path,
            httpMethod = httpMethod,
            queryParameters = operation.hikakuQueryParameters(),
            pathParameters = operation.hikakuPathParameters(),
            headerParameters = operation.hikakuHeaderParameters(),
            produces = extractProduceMediaTypes(operation),
            consumes = extractConsumesMediaTypes(operation)
    )

    private fun extractConsumesMediaTypes(operation: Operation?): Set<String> {
        return operation?.requestBody
                ?.content
                ?.keys
                .orEmpty()
                .union(extractConsumesFromComponents(operation))
                .toSet()
    }

    private fun extractConsumesFromComponents(operation: Operation?): Set<String> {
        return operation?.requestBody
                ?.`$ref`
                ?.let {
                    Regex("#/components/requestBodies/(?<key>.+)")
                            .find(it)
                            ?.groups
                            ?.get("key")
                            ?.value
                }
                ?.let {
                    openApi.components
                            .requestBodies[it]
                            ?.content
                            ?.keys
                }
                .orEmpty()
    }

    private fun extractProduceMediaTypes(operation: Operation?): Set<String> {
        return operation?.responses
                ?.flatMap {
                    it.value
                            ?.content
                            ?.keys
                            .orEmpty()
                }
                .orEmpty()
                .union(extractResponsesFromComponents(operation))
                .toSet()
    }

    private fun extractResponsesFromComponents(operation: Operation?): Set<String> {
        return operation?.responses
                ?.mapNotNull { it.value.`$ref` }
                ?.map {
                    Regex("#/components/responses/(?<key>.+)")
                            .find(it)
                            ?.groups
                            ?.get("key")
                            ?.value
                }
                ?.flatMap {
                    openApi.components
                            .responses[it]
                            ?.content
                            ?.keys
                            .orEmpty()
                }
                .orEmpty()
                .toSet()
    }

    companion object {
        private fun readFileContent(openApiSpecification: Path): String {
            try {
                openApiSpecification.checkFileValidity(".json", ".yaml", ".yml")
            } catch (throwable: Throwable) {
                throw SpecificationParserException(throwable)
            }

            val fileContent = readAllLines(openApiSpecification, UTF_8).joinToString("\n")

            if (fileContent.isBlank()) {
                throw SpecificationParserException("Given OpenAPI file is blank.")
            }

            return fileContent
        }
    }
}