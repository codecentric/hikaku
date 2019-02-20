package de.codecentric.hikaku.converter.spring.extensions

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

fun Map.Entry<RequestMappingInfo, HandlerMethod>.produces(): Set<String> {
    val isResponseBodyAnnotationOnClass = this.value
            .method
            .kotlinFunction
            ?.instanceParameter
            ?.type
            ?.jvmErasure
            ?.findAnnotation<ResponseBody>() != null

    val isResponseBodyAnnotationOnFunction = this.value
            .method
            .kotlinFunction
            ?.findAnnotation<ResponseBody>() != null

    val isErrorPath = this.key.patternsCondition.patterns.contains("/error")

    if (!isErrorPath && !isResponseBodyAnnotationOnClass && !isResponseBodyAnnotationOnFunction) {
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