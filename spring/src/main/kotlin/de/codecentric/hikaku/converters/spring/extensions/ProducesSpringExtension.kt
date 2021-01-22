package de.codecentric.hikaku.converters.spring.extensions

import de.codecentric.hikaku.extensions.isString
import de.codecentric.hikaku.extensions.isUnit
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.view.RedirectView
import java.lang.reflect.Method
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

internal fun Map.Entry<RequestMappingInfo, HandlerMethod>.produces(): Set<String> {
    val isNotErrorPath = this.key.patternsCondition?.patterns?.contains("/error") == false
    val hasNoResponseBodyAnnotation = !this.value.providesResponseBodyAnnotation()
    val hasNoRestControllerAnnotation = !this.value.providesRestControllerAnnotation()
    val hasHttpServletResponseParam = this.value.hasHttpServletResponseParam()

    if (isNotErrorPath && (hasNoResponseBodyAnnotation && hasNoRestControllerAnnotation)) {
        return emptySet()
    }

    if (isNotErrorPath && (this.value.method.hasNoReturnType() && !hasHttpServletResponseParam)) {
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

    val returnType = this.value
            .method
            .kotlinFunction
            ?.returnType
            ?.jvmErasure

    return if (returnType != null) {
        when {
            returnType.isString() -> setOf(TEXT_PLAIN_VALUE)
            returnType.isUnit() -> emptySet()
            returnType == RedirectView::class -> emptySet()
            else -> setOf(APPLICATION_JSON_VALUE)
        }
    } else {
        emptySet()
    }
}

private fun Method.hasNoReturnType() = this.returnType.kotlin.isUnit()

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

private fun HandlerMethod.hasHttpServletResponseParam(): Boolean {
    return this.methodParameters
        .any { it.parameterType.name == "javax.servlet.http.HttpServletResponse" }
}
