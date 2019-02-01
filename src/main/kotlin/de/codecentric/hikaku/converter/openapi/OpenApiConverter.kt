package de.codecentric.hikaku.converter.openapi

import de.codecentric.hikaku.converter.AbstractEndpointConverter
import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature.*
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.PathParameter
import de.codecentric.hikaku.endpoints.QueryParameter
import de.codecentric.hikaku.converter.openapi.extensions.ops
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.parser.OpenAPIV3Parser
import java.io.File
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files
import java.nio.file.Path

class OpenApiConverter(private val openApiSpecification: String) : AbstractEndpointConverter() {

    init {
        if (openApiSpecification.isBlank()) {
            throw IllegalArgumentException("Given specification is blank.")
        }
    }

    override val supportedFeatures = SupportedFeatures(
            QueryParameterName,
            PathParameter
    )

    override fun convert(): Set<Endpoint> {
        val endpoints = mutableSetOf<Endpoint>()

        OpenAPIV3Parser().readContents(openApiSpecification, null, null).let { openApi ->
            openApi.openAPI.paths.forEach { openApiPath ->
                openApiPath.value.ops().forEach { httpMethod: HttpMethod, operation: Operation? ->
                    endpoints.add(mapEndpoint(openApiPath, httpMethod, operation))
                }
            }
        }

        return endpoints
    }

    private fun mapEndpoint(
            path: Map.Entry<String, PathItem>,
            httpMethod: HttpMethod,
            operation: Operation?
    ): Endpoint {
        val queryParameters = mutableSetOf<QueryParameter>()
        val pathParameters = mutableSetOf<PathParameter>()

        operation?.parameters?.forEach {
            when (it) {
                is io.swagger.v3.oas.models.parameters.QueryParameter -> queryParameter += QueryParameter(it.name, it.required)
                is io.swagger.v3.oas.models.parameters.PathParameter -> pathParameter += PathParameter(it.name)
            }
        }

        return Endpoint(
                path = path.key,
                httpMethod = httpMethod,
                queryParameters = queryParameters,
                pathParameters = pathParameters
        )
    }

    companion object {
        operator fun invoke(openApiSpecification: Path): OpenApiConverter {
            checkFileValidity(openApiSpecification)

            val expectedFileContent = Files.readAllLines(openApiSpecification, UTF_8).joinToString("\n")

            return OpenApiConverter(expectedFileContent)
        }

        private fun checkFileValidity(openApiSpecification: Path) {
            if (!Files.exists(openApiSpecification)) {
                throw IllegalArgumentException("Given specification file does not exist.")
            }

            if (!Files.isRegularFile(openApiSpecification)) {
                throw IllegalArgumentException("Given specification file is not a regular file.")
            }

            if (!isValidFileExtension(openApiSpecification)) {
                throw IllegalArgumentException("Given file is not of type *.json, *.yaml or *.yml.")
            }
        }

        operator fun invoke(openApiSpecification: File): OpenApiConverter {
            return OpenApiConverter(openApiSpecification.toPath())
        }

        private fun isValidFileExtension(openApiSpecification: Path): Boolean {
            val fileName = openApiSpecification.fileName.toString()

            return fileName.endsWith(".json")
                    || fileName.endsWith(".yaml")
                    || fileName.endsWith(".yml")
        }
    }
}