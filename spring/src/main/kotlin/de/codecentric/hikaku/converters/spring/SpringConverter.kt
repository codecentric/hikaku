package de.codecentric.hikaku.converters.spring

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.spring.extensions.*
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.springframework.context.ApplicationContext
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

/**
 * Extracts and converts [Endpoint]s from a spring [ApplicationContext].
 * @param applicationContext Spring application context
 */
class SpringConverter(private val applicationContext: ApplicationContext) : AbstractEndpointConverter() {

    override val supportedFeatures = SupportedFeatures(
            Feature.QueryParameters,
            Feature.PathParameters,
            Feature.HeaderParameters,
            Feature.MatrixParameters,
            Feature.Produces,
            Feature.Consumes,
            Feature.Deprecation
    )

    override fun convert(): Set<Endpoint> {
        return applicationContext.getBean(RequestMappingHandlerMapping::class.java)
                .handlerMethods
                .flatMap { mappingEntry ->
                    mappingEntry.key.paths().flatMap { path ->
                        createEndpoints(path, mappingEntry)
                    }
                }
                .toSet()
    }

    private fun createEndpoints(path: String, mappingEntry: Map.Entry<RequestMappingInfo, HandlerMethod>): Set<Endpoint> {
        val httpMethods = extractAvailableHttpMethods(mappingEntry)
        val cleanedPath = removeRegex(path)

        val endpoints = httpMethods.map {
            Endpoint(
                    path = cleanedPath,
                    httpMethod = it,
                    queryParameters = mappingEntry.value.hikakuQueryParameters(),
                    pathParameters = mappingEntry.value.hikakuPathParameters(),
                    headerParameters = mappingEntry.value.hikakuHeaderParameters(),
                    matrixParameters = mappingEntry.value.hikakuMatrixParameters(),
                    produces = mappingEntry.produces(),
                    consumes = mappingEntry.consumes(),
                    deprecated = mappingEntry.isEndpointDeprecated()
            )
        }
        .toMutableSet()

        // Spring always adds an OPTIONS http method if it does not exist, but without query and path parameter
        if (!httpMethods.contains(OPTIONS)) {
            endpoints.add(
                    Endpoint(
                            path = cleanedPath,
                            httpMethod = OPTIONS,
                            deprecated = mappingEntry.isEndpointDeprecated()
                    )
            )
        }

        return endpoints
    }

    private fun removeRegex(path: String): String {
        return path.split('/').joinToString("/") { pathSegment ->
            pathSegment.let {
                when {
                    it.contains(':') -> it.replace(Regex(":.*"), "}")
                    else -> it
                }
            }
        }
    }

    private fun extractAvailableHttpMethods(mappingEntry: Map.Entry<RequestMappingInfo, HandlerMethod>): Set<HttpMethod> {
        val httpMethods = mappingEntry.key.hikakuHttpMethods()

        // Spring adds all http methods except for TRACE if no http method has been set explicitly
        // OPTIONS is a special case. If it's not added manually it has to be added without any path or query parameters
        return if (httpMethods.isEmpty()) {
            HttpMethod.values()
                    .filterNot { it == TRACE }
                    .filterNot { it == OPTIONS }
                    .toSet()
        } else {
            // Spring always adds a HEAD http method
            httpMethods + HEAD
        }
    }

    companion object {
        @JvmField
        val IGNORE_ERROR_ENDPOINT: (Endpoint) -> Boolean = { endpoint -> endpoint.path == "/error" }
    }
}
