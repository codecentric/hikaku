package de.codecentric.hikaku.converters.spring.extensions

import org.springframework.http.MediaType.ALL_VALUE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

internal fun Map.Entry<RequestMappingInfo, HandlerMethod>.consumes(): Set<String> {
    val consumes = this.key
            .consumesCondition
            .expressions
            .map { it.mediaType.toString() }
            .toSet()

    if (consumes.isNotEmpty()) {
        return consumes
    }

    val providesRequestBodyAnnotation = this.value
            .method
            .kotlinFunction
            ?.parameters
            ?.any {
                it.findAnnotation<RequestBody>() !== null
            } ?: false

    if (!providesRequestBodyAnnotation) {
        return emptySet()
    }

    val isParameterString = this.value
            .method
            .kotlinFunction
            ?.parameters
            ?.firstOrNull {
                it.findAnnotation<RequestBody>() !== null
            }
            ?.type
            ?.jvmErasure
            ?.let {
                it.java == java.lang.String::class.java
            } ?: false

    return if (isParameterString) {
        setOf(ALL_VALUE)
    } else {
        setOf(APPLICATION_JSON_VALUE)
    }
}
