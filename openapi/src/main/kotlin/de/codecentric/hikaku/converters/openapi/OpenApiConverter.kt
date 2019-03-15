package de.codecentric.hikaku.converters.openapi

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.EndpointConverterException
import de.codecentric.hikaku.converters.openapi.extensions.httpMethods
import de.codecentric.hikaku.converters.openapi.extractors.*
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.extensions.checkFileValidity
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.parser.OpenAPIV3Parser
import java.io.File
import java.lang.RuntimeException
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files.readAllLines
import java.nio.file.Path
import io.swagger.v3.oas.models.parameters.HeaderParameter as OpenApiHeaderParameter
import io.swagger.v3.oas.models.parameters.PathParameter as OpenApiPathParameter
import io.swagger.v3.oas.models.parameters.QueryParameter as OpenApiQueryParameter

/**
 * Extracts and converts [Endpoint]s from OpenAPI 3.0.X document. Either a *.yaml*, *.yml* or a *.json* file.
 */
class OpenApiConverter private constructor(private val specificationContent: String) : AbstractEndpointConverter() {

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
            throw EndpointConverterException(throwable)
        }
    }

    private fun parseOpenApi(): Set<Endpoint> {
        val swaggerParseResult = OpenAPIV3Parser().readContents(specificationContent, null, null)

        val openApi = swaggerParseResult.openAPI ?: throw OpenApiParseException(swaggerParseResult.messages)

        val consumesExtractor = ConsumesExtractor(openApi)
        val producesExtractor = ProducesExtractor(openApi)
        val queryParameterExtractor = QueryParameterExtractor(openApi)
        val headerParameterExtractor = HeaderParameterExtractor(openApi)
        val pathParameterExtractor = PathParameterExtractor(openApi)

        return openApi.paths.flatMap { (path, pathItem) ->
            pathItem.httpMethods().map { (httpMethod: HttpMethod, operation: Operation?) ->
                Endpoint(
                        path = path,
                        httpMethod = httpMethod,
                        queryParameters = queryParameterExtractor.extractQueryParameters(operation),
                        pathParameters = pathParameterExtractor.extractPathParameters(operation),
                        headerParameters = headerParameterExtractor.extractHeaderParameters(operation),
                        consumes = consumesExtractor.extractConsumesMediaTypes(operation),
                        produces = producesExtractor.extractProduceMediaTypes(operation)
                )
            }
        }
        .toSet()
    }

    companion object {
        private fun readFileContent(openApiSpecification: Path): String {
            try {
                openApiSpecification.checkFileValidity(".json", ".yaml", ".yml")
            } catch (throwable: Throwable) {
                throw EndpointConverterException(throwable)
            }

            val fileContent = readAllLines(openApiSpecification, UTF_8).joinToString("\n")

            if (fileContent.isBlank()) {
                throw EndpointConverterException("Given OpenAPI file is blank.")
            }

            return fileContent
        }
    }
}

class OpenApiParseException(reasons: List<String>)
    : RuntimeException("Failed to parse OpenApi spec. Reasons:\n${reasons.joinToString("\n")}")