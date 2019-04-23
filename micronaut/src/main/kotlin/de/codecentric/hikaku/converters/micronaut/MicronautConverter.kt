package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.ClassLocator
import de.codecentric.hikaku.converters.EndpointConverterException
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.PathParameter
import de.codecentric.hikaku.endpoints.QueryParameter
import io.micronaut.http.annotation.*
import java.lang.reflect.Method
import kotlin.reflect.jvm.kotlinFunction

class MicronautConverter(private val packageName: String) : AbstractEndpointConverter() {

    override val supportedFeatures = SupportedFeatures()

    override fun convert(): Set<Endpoint> {
        if (packageName.isBlank()) {
            throw EndpointConverterException("Package name must not be blank.")
        }

        return ClassLocator.getClasses(packageName)
                .filter { it.getAnnotation(Controller::class.java) != null }
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
            method.isAnnotationPresent(Delete::class.java) -> true
            method.isAnnotationPresent(Get::class.java) -> true
            method.isAnnotationPresent(Head::class.java) -> true
            method.isAnnotationPresent(Options::class.java) -> true
            method.isAnnotationPresent(Patch::class.java) -> true
            method.isAnnotationPresent(Post::class.java) -> true
            method.isAnnotationPresent(Put::class.java) -> true
            else -> false
        }
    }

    private fun createEndpoint(resource: Class<*>, method: Method): Endpoint {
        val path = extractPath(resource, method)

        return Endpoint(
                path = path,
                httpMethod = extractHttpMethod(method),
                queryParameters = extractQueryParameters(path, method),
                pathParameters = extractPathParameters(path, method)
        )
    }

    private fun extractPath(resource: Class<*>, method: Method): String {
        var pathOnClass = resource.getAnnotation(Controller::class.java).value
        val pathOnFunction = when {
            method.isAnnotationPresent(Delete::class.java) -> method.getAnnotation(Delete::class.java).value
            method.isAnnotationPresent(Get::class.java) -> method.getAnnotation(Get::class.java).value
            method.isAnnotationPresent(Head::class.java) -> method.getAnnotation(Head::class.java).value
            method.isAnnotationPresent(Options::class.java) -> method.getAnnotation(Options::class.java).value
            method.isAnnotationPresent(Patch::class.java) -> method.getAnnotation(Patch::class.java).value
            method.isAnnotationPresent(Post::class.java) -> method.getAnnotation(Post::class.java).value
            method.isAnnotationPresent(Put::class.java) -> method.getAnnotation(Put::class.java).value
            else -> ""
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
            method.isAnnotationPresent(Delete::class.java) -> HttpMethod.DELETE
            method.isAnnotationPresent(Get::class.java) -> HttpMethod.GET
            method.isAnnotationPresent(Head::class.java) -> HttpMethod.HEAD
            method.isAnnotationPresent(Options::class.java) -> HttpMethod.OPTIONS
            method.isAnnotationPresent(Patch::class.java) -> HttpMethod.PATCH
            method.isAnnotationPresent(Post::class.java) -> HttpMethod.POST
            method.isAnnotationPresent(Put::class.java) -> HttpMethod.PUT
            else -> throw IllegalStateException("Unable to determine http method. Valid annotation not found.")
        }
    }

    private fun extractQueryParameters(path: String, method: Method): Set<QueryParameter> {
        return method.parameters
                .filter { it.isAnnotationPresent(QueryValue::class.java) }
                .map { it.getAnnotation(QueryValue::class.java) }
                .map { it as QueryValue }
                .map { QueryParameter(it.value, it.defaultValue.isBlank()) }
                .toSet()
    }

    private fun extractPathParameters(path: String, method: Method): Set<PathParameter> {
        val parameters = method.parameters
                .filter { it.isAnnotationPresent(PathVariable::class.java) }
                .map { it.getAnnotation(PathVariable::class.java) }
                .map { it as PathVariable }
                .map {
                    val pathParameter = if (it.value.isNotBlank()) {
                        it.value
                    } else {
                        it.name
                    }

                    PathParameter(pathParameter)
                }
                .toMutableSet()

        val methodParametersWithoutAnnotation = method.kotlinFunction
                ?.parameters
                ?.filter { it.annotations.isEmpty() }
                ?.map { it.name }
                .orEmpty()

        val pathParametersWithoutAnnotation = Regex("\\{.+\\}").findAll(path)
                .map { it.value }
                .map { it.removePrefix("{") }
                .map { it.removeSuffix("}") }
                .filter { methodParametersWithoutAnnotation.contains(it) }
                .map { PathParameter(it) }
                .toSet()

        parameters.addAll(pathParametersWithoutAnnotation)

        return parameters
    }
}