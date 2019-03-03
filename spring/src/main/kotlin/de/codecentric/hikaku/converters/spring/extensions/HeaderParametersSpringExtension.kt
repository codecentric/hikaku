package de.codecentric.hikaku.converters.spring.extensions

import de.codecentric.hikaku.endpoints.HeaderParameter
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ValueConstants
import org.springframework.web.method.HandlerMethod
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.kotlinFunction

internal fun HandlerMethod.hikakuHeaderParameters(): Set<HeaderParameter> {
    val method = this.method.kotlinFunction ?: return emptySet()

    return method.parameters
            .filter { it.annotations.filterIsInstance<RequestHeader>().any() }
            .map { extractHeaderParameter(it) }
            .toSet()
}

private fun extractHeaderParameter(it: KParameter): HeaderParameter {
    val requestHeader = it.annotations.find { it is RequestHeader } as RequestHeader
    val parameterName = extractHeaderParameterName(requestHeader, it)
    val isRequired = isHeaderParameterRequired(requestHeader)

    return HeaderParameter(parameterName, isRequired)
}

private fun isHeaderParameterRequired(requestHeader: RequestHeader): Boolean {
    if (requestHeader.defaultValue == ValueConstants.DEFAULT_NONE) {
        return requestHeader.required
    }

    return false
}

private fun extractHeaderParameterName(requestHeader: RequestHeader, it: KParameter): String {
    if (requestHeader.value.isNotBlank() && requestHeader.name.isNotBlank()) {
        throw IllegalStateException("Both 'value' and 'name' attribute are provided for header parameter '${it.name}'. Only one is permitted.")
    }

    return when {
        requestHeader.value.isNotBlank() -> requestHeader.value
        requestHeader.name.isNotBlank() -> requestHeader.name
        else -> it.name ?: ""
    }
}