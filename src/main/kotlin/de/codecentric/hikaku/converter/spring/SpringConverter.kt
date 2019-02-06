package de.codecentric.hikaku.converter.spring

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converter.AbstractEndpointConverter
import de.codecentric.hikaku.converter.spring.extensions.*
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

/**
 * Extracts and converts [Endpoint]s from a spring [ApplicationContext].
 * @param applicationContext Spring application context
 */
class SpringConverter(
        private val applicationContext: ApplicationContext
) : AbstractEndpointConverter() {

    override val supportedFeatures = SupportedFeatures(
            Feature.QueryParameterName,
            Feature.PathParameter,
            Feature.HeaderParameterName,
            Feature.HeaderParameterRequired
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
                    queryParameters = mappingEntry.value.queryParameter(),
                    pathParameters = mappingEntry.value.pathParameter(),
                    headerParameters = mappingEntry.value.headerParameter(),
                    produces = extractProducesMediaType(mappingEntry)
            )
        }
        .toMutableSet()

        // Spring always adds an OPTIONS http method if it does not exist, but without query and path parameter
        if(!httpMethods.contains(OPTIONS)) {
            endpoints.add(
                    Endpoint(
                            path = cleanedPath,
                            httpMethod = OPTIONS
                    )
            )
        }

        return endpoints
    }

    private fun extractProducesMediaType(mappingEntry: Map.Entry<RequestMappingInfo, HandlerMethod>): Set<String> {
        val produces = mappingEntry.key.produces()

        if (produces.isEmpty()) {
            if (mappingEntry.value.method.returnType == String::class.java) {
                return setOf(TEXT_PLAIN_VALUE)
            }

            return setOf(APPLICATION_JSON_UTF8_VALUE)
        }

        return produces
    }

    private fun removeRegex(path: String): String {
        if (path.contains(':')) {
            return path.replace(Regex(":.*?}"), "}")
        }

        return path
    }

    private fun extractAvailableHttpMethods(mappingEntry: Map.Entry<RequestMappingInfo, HandlerMethod>): Set<HttpMethod> {
        val httpMethods = mappingEntry.key.httpMethods()

        // Spring adds all http methods except for trace if no http method has been set explicitly
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
        const val IGNORE_ERROR_ENDPOINT = "/error"
    }
}