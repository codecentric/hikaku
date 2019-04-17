package de.codecentric.hikaku.converters.jaxrs

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.ClassLocator
import de.codecentric.hikaku.converters.EndpointConverterException
import de.codecentric.hikaku.endpoints.*
import de.codecentric.hikaku.endpoints.HttpMethod
import java.lang.reflect.Method
import javax.ws.rs.*

class JaxRsConverter(private val packageName: String) : AbstractEndpointConverter() {

    override val supportedFeatures = SupportedFeatures(
            Feature.QueryParameters,
            Feature.PathParameters,
            Feature.HeaderParameters,
            Feature.MatrixParameters,
            Feature.Consumes,
            Feature.Produces
    )

    override fun convert(): Set<Endpoint> {
        if (packageName.isBlank()) {
            throw EndpointConverterException("Package name must not be blank.")
        }

        return ClassLocator.getClasses(packageName)
                .filter { it.getAnnotation(Path::class.java) != null }
                .flatMap { extractEndpoints(it) }
                .toSet()
    }

    private fun extractEndpoints(resource: Class<*>): List<Endpoint> {
        return resource.methods
                .filter { isHttpMethodAnnotationPresent(it) }
                .map { createEndpoint(resource, it) }
    }

    private fun isHttpMethodAnnotationPresent(method: Method): Boolean {
        return when {
            method.isAnnotationPresent(DELETE::class.java) -> true
            method.isAnnotationPresent(GET::class.java) -> true
            method.isAnnotationPresent(HEAD::class.java) -> true
            method.isAnnotationPresent(OPTIONS::class.java) -> true
            method.isAnnotationPresent(PATCH::class.java) -> true
            method.isAnnotationPresent(POST::class.java) -> true
            method.isAnnotationPresent(PUT::class.java) -> true
            else -> false
        }
    }

    private fun createEndpoint(resource: Class<*>, method: Method) = Endpoint(
            path = extractPath(resource, method),
            httpMethod = extractHttpMethod(method),
            pathParameters = extractPathParameters(method),
            queryParameters = extractQueryParameters(method),
            headerParameters = extractHeaderParameters(method),
            matrixParameters = extractMatrixParameters(method),
            produces = extractProduces(resource, method),
            consumes = extractConsumes(resource, method)
    )

    private fun extractPath(resource: Class<*>, method: Method): String {
        var pathOnClass = resource.getAnnotation(Path::class.java).value
        val pathOnFunction = if (method.isAnnotationPresent(Path::class.java)) {
            method.getAnnotation(Path::class.java).value
        } else {
            ""
        }

        if (!pathOnClass.startsWith("/")) {
            pathOnClass = "/$pathOnClass"
        }

        val combinedPath = "$pathOnClass/$pathOnFunction".replace(Regex("/+"), "/")

        return if (combinedPath.endsWith('/')) {
            combinedPath.substringBeforeLast('/')
        } else {
            combinedPath
        }
    }

    private fun extractHttpMethod(method: Method): HttpMethod {
        return when {
            method.isAnnotationPresent(DELETE::class.java) -> HttpMethod.DELETE
            method.isAnnotationPresent(GET::class.java) -> HttpMethod.GET
            method.isAnnotationPresent(HEAD::class.java) -> HttpMethod.HEAD
            method.isAnnotationPresent(OPTIONS::class.java) -> HttpMethod.OPTIONS
            method.isAnnotationPresent(PATCH::class.java) -> HttpMethod.PATCH
            method.isAnnotationPresent(POST::class.java) -> HttpMethod.POST
            method.isAnnotationPresent(PUT::class.java) -> HttpMethod.PUT
            else -> throw IllegalStateException("Unable to determine http method. Valid annotation not found.")
        }
    }

    private fun extractProduces(resource: Class<*>, method: Method): Set<String> {
        val annotationValue = when {
            method.isAnnotationPresent(Produces::class.java) -> method.getAnnotation(Produces::class.java).value.toSet()
            resource.isAnnotationPresent(Produces::class.java) -> resource.getAnnotation(Produces::class.java).value.toSet()
            else -> setOf("*/*")
        }

        return if (method.returnType.name == "void" || method.returnType.name == "java.lang.Void") {
            emptySet()
        } else {
            annotationValue
        }
    }

    private fun extractConsumes(resource: Class<*>, method: Method): Set<String> {
        val annotationValue = when {
            method.isAnnotationPresent(Consumes::class.java) -> method.getAnnotation(Consumes::class.java).value.toSet()
            resource.isAnnotationPresent(Consumes::class.java) -> resource.getAnnotation(Consumes::class.java).value.toSet()
            else -> setOf("*/*")
        }

        return if (containsRequestBody(method)) {
            annotationValue
        } else {
            emptySet()
        }
    }

    private fun containsRequestBody(method: Method): Boolean {
        return method.parameters
                .filterNot { it.isAnnotationPresent(BeanParam::class.java) }
                .filterNot { it.isAnnotationPresent(CookieParam::class.java) }
                .filterNot { it.isAnnotationPresent(DefaultValue::class.java) }
                .filterNot { it.isAnnotationPresent(Encoded::class.java) }
                .filterNot { it.isAnnotationPresent(FormParam::class.java) }
                .filterNot { it.isAnnotationPresent(HeaderParam::class.java) }
                .filterNot { it.isAnnotationPresent(MatrixParam::class.java) }
                .filterNot { it.isAnnotationPresent(PathParam::class.java) }
                .filterNot { it.isAnnotationPresent(QueryParam::class.java) }
                .isNotEmpty()
    }

    private fun extractQueryParameters(method: Method): Set<QueryParameter> {
        return method.parameters
                .filter { it.isAnnotationPresent(QueryParam::class.java) }
                .map { it.getAnnotation(QueryParam::class.java) }
                .map { (it as QueryParam).value }
                .map { QueryParameter(it, false) }
                .toSet()
    }

    private fun extractPathParameters(method: Method): Set<PathParameter> {
        return method.parameters
                .filter { it.isAnnotationPresent(PathParam::class.java) }
                .map { it.getAnnotation(PathParam::class.java) }
                .map { (it as PathParam).value }
                .map { PathParameter(it) }
                .toSet()
    }

    private fun extractHeaderParameters(method: Method): Set<HeaderParameter> {
        return method.parameters
                .filter { it.isAnnotationPresent(HeaderParam::class.java) }
                .map { it.getAnnotation(HeaderParam::class.java) }
                .map { (it as HeaderParam).value }
                .map { HeaderParameter(it) }
                .toSet()
    }

    private fun extractMatrixParameters(method: Method): Set<MatrixParameter> {
        return method.parameters
                .filter { it.isAnnotationPresent(MatrixParam::class.java) }
                .map { it.getAnnotation(MatrixParam::class.java) }
                .map { (it as MatrixParam).value }
                .map { MatrixParameter(it) }
                .toSet()
    }
}