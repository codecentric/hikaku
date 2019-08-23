package de.codecentric.hikaku.converters.spring.extensions

import de.codecentric.hikaku.endpoints.QueryParameter
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ValueConstants
import org.springframework.web.method.HandlerMethod
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.kotlinFunction

internal fun HandlerMethod.hikakuQueryParameters(): Set<QueryParameter> {
    val method = this.method.kotlinFunction ?: return emptySet()

    return method.parameters
            .filter { it.annotations.filterIsInstance<RequestParam>().any() }
            .map { extractQueryParameter(it) }
            .toSet()
}

private fun extractQueryParameter(it: KParameter): QueryParameter {
    val requestParam = it.annotations.find { it is RequestParam } as RequestParam
    val parameterName = extractQueryParameterName(requestParam, it)
    val isRequired = isQueryParameterRequired(requestParam)

    return QueryParameter(parameterName, isRequired)
}

private fun isQueryParameterRequired(requestParam: RequestParam): Boolean {
    if (requestParam.defaultValue == ValueConstants.DEFAULT_NONE) {
        return requestParam.required
    }

    return false
}

private fun extractQueryParameterName(requestParam: RequestParam, it: KParameter): String {
    check(!(requestParam.value.isNotBlank() && requestParam.name.isNotBlank())) {
        "Both 'value' and 'name' attribute are provided for query parameter '${it.name}'. Only one is permitted."
    }

    return when {
        requestParam.value.isNotBlank() -> requestParam.value
        requestParam.name.isNotBlank() -> requestParam.name
        else -> it.name ?: ""
    }
}