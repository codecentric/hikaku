package de.codecentric.hikaku.converter.spring.extensions

import org.springframework.http.MediaType.ALL_VALUE
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

fun Map.Entry<RequestMappingInfo, HandlerMethod>.consumes(): Set<String> {
    val providesRequestBodyAnnotation = this.value
            .method
            .kotlinFunction
            ?.parameters
            ?.any {
                it.annotations
                        .filterIsInstance<RequestBody>()
                        .any()
            } ?: false

    if (!providesRequestBodyAnnotation) {
        return emptySet()
    }

    val consumes = this.key
            .consumesCondition
            .expressions
            .map { it.mediaType.toString() }
            .toSet()

    if (consumes.isNotEmpty()) {
        return consumes
    }

    val isParameterString = this.value
            .method
            .kotlinFunction
            ?.parameters
            ?.firstOrNull {
                it.annotations
                        .filterIsInstance<RequestBody>()
                        .any()
            }
            ?.type
            ?.jvmErasure
            ?.let {
                it.java == java.lang.String::class.java
            } ?: false

    return if (isParameterString) {
        setOf(ALL_VALUE)
    } else {
        setOf(APPLICATION_JSON_UTF8_VALUE)
    }
}