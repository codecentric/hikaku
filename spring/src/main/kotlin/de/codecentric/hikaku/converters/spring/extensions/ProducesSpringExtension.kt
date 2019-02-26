package de.codecentric.hikaku.converters.spring.extensions

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

internal fun Map.Entry<RequestMappingInfo, HandlerMethod>.produces(): Set<String> {
    val isErrorPath = this.key.patternsCondition.patterns.contains("/error")

    if (!isErrorPath && !this.value.providesResponseBodyAnnotation() && !this.value.providesRestControllerAnnotation()) {
        return emptySet()
    }

    val produces = this.key
            .producesCondition
            .expressions
            .map { it.mediaType.toString() }
            .toSet()

    if (produces.isNotEmpty()) {
        return produces
    }

    val isParameterString = this.value
            .method
            .kotlinFunction
            ?.returnType
            ?.jvmErasure
            ?.java == java.lang.String::class.java

    return if (isParameterString) {
        setOf(TEXT_PLAIN_VALUE)
    } else {
        setOf(APPLICATION_JSON_UTF8_VALUE)
    }
}

private fun HandlerMethod.providesRestControllerAnnotation() = this.method
        .kotlinFunction
        ?.instanceParameter
        ?.type
        ?.jvmErasure
        ?.findAnnotation<RestController>() != null

private fun HandlerMethod.providesResponseBodyAnnotation() = isResponseBodyAnnotationOnClass() || isResponseBodyAnnotationOnFunction()

private fun HandlerMethod.isResponseBodyAnnotationOnClass() = this.method
        .kotlinFunction
        ?.instanceParameter
        ?.type
        ?.jvmErasure
        ?.findAnnotation<ResponseBody>() != null


private fun HandlerMethod.isResponseBodyAnnotationOnFunction() = this.method
        .kotlinFunction
        ?.findAnnotation<ResponseBody>() != null