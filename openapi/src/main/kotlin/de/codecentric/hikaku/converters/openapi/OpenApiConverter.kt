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
import java.nio.charset.Charset
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

    @JvmOverloads
    constructor(openApiSpecification: File, charset: Charset = UTF_8): this(openApiSpecification.toPath(), charset)
    @JvmOverloads
    constructor(openApiSpecification: Path, charset: Charset = UTF_8): this(readFileContent(openApiSpecification, charset))

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

        val openApi = swaggerParseResult.openAPI ?: throw openApiParseException(swaggerParseResult.messages)

        val extractConsumesMediaTypes = ConsumesExtractor(openApi)
        val extractProduceMediaTypes = ProducesExtractor(openApi)
        val extractQueryParameters = QueryParameterExtractor(openApi)
        val extractHeaderParameters = HeaderParameterExtractor(openApi)
        val extractPathParameters = PathParameterExtractor(openApi)

        return openApi.paths.flatMap { (path, pathItem) ->
            pathItem.httpMethods().map { (httpMethod: HttpMethod, operation: Operation?) ->
                Endpoint(
                        path = path,
                        httpMethod = httpMethod,
                        queryParameters = extractQueryParameters(operation),
                        pathParameters = extractPathParameters(operation),
                        headerParameters = extractHeaderParameters(operation),
                        consumes = extractConsumesMediaTypes(operation),
                        produces = extractProduceMediaTypes(operation)
                )
            }
        }
        .toSet()
    }
}

private fun readFileContent(openApiSpecification: Path, charset: Charset): String {
    try {
        openApiSpecification.checkFileValidity(".json", ".yaml", ".yml")
    } catch (throwable: Throwable) {
        throw EndpointConverterException(throwable)
    }
    val fileContent = readAllLines(openApiSpecification, charset).joinToString("\n")

    if (fileContent.isBlank()) {
        throw EndpointConverterException("Given OpenAPI file is blank.")
    }

    return fileContent
}

private fun openApiParseException(reasons: List<String>)
    = EndpointConverterException("Failed to parse OpenAPI spec. Reasons:\n${reasons.joinToString("\n")}")