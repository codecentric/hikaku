package de.codecentric.hikaku.converter.spring

import de.codecentric.hikaku.converter.AbstractEndpointConverter
import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature.PathParameter
import de.codecentric.hikaku.SupportedFeatures.Feature.QueryParameterName
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.HttpMethod.HEAD
import de.codecentric.hikaku.endpoints.HttpMethod.TRACE
import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS
import de.codecentric.hikaku.converter.spring.extensions.httpMethods
import de.codecentric.hikaku.converter.spring.extensions.pathParameter
import de.codecentric.hikaku.converter.spring.extensions.paths
import de.codecentric.hikaku.converter.spring.extensions.queryParameter
import org.springframework.context.ApplicationContext
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

class SpringConverter(
        private val applicationContext: ApplicationContext
) : AbstractEndpointConverter() {

    override val supportedFeatures = SupportedFeatures(
            QueryParameterName,
            PathParameter
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
                    queryParameter = mappingEntry.value.queryParameter(),
                    pathParameter = mappingEntry.value.pathParameter()
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

    private fun removeRegex(path: String): String {
        if (path.contains(':')) {
            return path.replace(Regex(":.*?}"), "}")
        }

        return path
    }

    private fun extractAvailableHttpMethods(mappingEntry: Map.Entry<RequestMappingInfo, HandlerMethod>): Set<HttpMethod> {
        var httpMethods = mappingEntry.key.httpMethods()

        // Spring adds all http methods except for trace if no http method has been set explicitly
        // OPTIONS is a special case. If it's not added manually it has to be added without any path or query parameters
        if (httpMethods.isEmpty()) {
            httpMethods = HttpMethod.values()
                    .filterNot { it == TRACE }
                    .filterNot { it == OPTIONS }
                    .toSet()
        }

        // Spring always adds a HEAD http method
        httpMethods += HEAD

        return httpMethods
    }

    companion object {
        val IGNORE_ERROR_ENDPOINTS = hashSetOf(
                "/error"
        )
    }
}