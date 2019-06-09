package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.ClassLocator
import de.codecentric.hikaku.converters.EndpointConverterException
import de.codecentric.hikaku.endpoints.*
import io.micronaut.http.annotation.*
import java.lang.reflect.Method
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.kotlinFunction

class MicronautConverter(private val packageName: String) : AbstractEndpointConverter() {

    override val supportedFeatures = SupportedFeatures(
        Feature.QueryParameters,
        Feature.PathParameters,
        Feature.HeaderParameters,
        Feature.Produces,
        Feature.Consumes,
        Feature.Deprecation
    )

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
                pathParameters = extractPathParameters(path, method),
                headerParameters = extractHeaderParameters(method),
                consumes = extractConsumes(resource, method),
                produces = extractProduces(resource, method),
                deprecated = isEndpointDeprecated(method)
        )
    }

    private fun extractProduces(resource: Class<*>, method: Method): Set<String> {
        val methodHasNoReturnType = method.returnType.name == "void" || method.returnType.name == "java.lang.Void"

        if (methodHasNoReturnType) {
            return emptySet()
        }

        val mediaTypesOnFunction = method.kotlinFunction
                ?.annotations
                ?.filterIsInstance<Produces>()
                ?.flatMap { it.value.map { entry -> entry } }
                ?.toSet()
                .orEmpty()

        if (mediaTypesOnFunction.isNotEmpty()) {
            return mediaTypesOnFunction
        }

        val mediaTypesOnControllerByConsumesAnnotation = resource.getAnnotation(Produces::class.java)
                ?.value
                ?.toSet()
                .orEmpty()

        if (mediaTypesOnControllerByConsumesAnnotation.isNotEmpty()) {
            return mediaTypesOnControllerByConsumesAnnotation
        }

        val mediaTypesDefinedByControllerAnnotation = resource.getAnnotation(Controller::class.java)
                .produces
                .toSet()

        if (mediaTypesDefinedByControllerAnnotation.isNotEmpty()) {
            return mediaTypesDefinedByControllerAnnotation
        }

        return setOf("application/json")
    }

    private fun extractConsumes(resource: Class<*>, method: Method): Set<String> {
        val methodAwaitsPayload = method.kotlinFunction
                ?.parameters
                ?.any { it.findAnnotation<Body>() != null }
                ?: false

        if (!methodAwaitsPayload) {
            return emptySet()
        }

        val mediaTypesOnFunction = method.kotlinFunction
                ?.annotations
                ?.filterIsInstance<Consumes>()
                ?.flatMap { it.value.map { entry -> entry } }
                ?.toSet()
                .orEmpty()

        if (mediaTypesOnFunction.isNotEmpty()) {
            return mediaTypesOnFunction
        }

        val mediaTypesOnControllerByConsumesAnnotation = resource.getAnnotation(Consumes::class.java)
                ?.value
                ?.toSet()
                .orEmpty()

        if (mediaTypesOnControllerByConsumesAnnotation.isNotEmpty()) {
            return mediaTypesOnControllerByConsumesAnnotation
        }

        val mediaTypesDefinedByControllerAnnotation = resource.getAnnotation(Controller::class.java)
                .consumes
                .toSet()

        if (mediaTypesDefinedByControllerAnnotation.isNotEmpty()) {
            return mediaTypesDefinedByControllerAnnotation
        }

        return setOf("application/json")
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
        val queryParameters = method.parameters
                .filter { it.isAnnotationPresent(QueryValue::class.java) }
                .map { it.getAnnotation(QueryValue::class.java) }
                .map { it as QueryValue }
                .map { QueryParameter(it.value, it.defaultValue.isBlank()) }
                .toMutableSet()


        val queryParameterWithoutAnnotation = methodParametersWithoutAnnotation(method).filterNot { templatesInPath(path).contains(it) }
                .filterNotNull()
                .map { QueryParameter(it, false) }
                .toSet()

        queryParameters.addAll(queryParameterWithoutAnnotation)

        return queryParameters
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

        val pathParametersWithoutAnnotation = templatesInPath(path)
                .filter { methodParametersWithoutAnnotation(method).contains(it) }
                .map { PathParameter(it) }
                .toSet()

        parameters.addAll(pathParametersWithoutAnnotation)

        return parameters
    }

    private fun methodParametersWithoutAnnotation(method: Method) = method.kotlinFunction
        ?.parameters
        ?.filter { it.annotations.isEmpty() }
        ?.map { it.name }
        .orEmpty()

    private fun templatesInPath(path: String) = Regex("\\{.+\\}").findAll(path)
        .map { it.value }
        .map { it.removePrefix("{") }
        .map { it.removeSuffix("}") }
        .toSet()

    private fun extractHeaderParameters(method: Method): Set<HeaderParameter> {
        return method.parameters
                .filter { it.isAnnotationPresent(Header::class.java) }
                .map { it.getAnnotation(Header::class.java) }
                .map { it as Header }
                .map { HeaderParameter(it.value, it.defaultValue.isBlank()) }
                .toSet()
    }

    private fun isEndpointDeprecated(method: Method) =
            method.isAnnotationPresent(Deprecated::class.java)
                    || method.declaringClass.isAnnotationPresent(Deprecated::class.java)
}