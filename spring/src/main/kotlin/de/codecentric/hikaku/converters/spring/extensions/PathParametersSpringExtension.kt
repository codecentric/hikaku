package de.codecentric.hikaku.converters.spring.extensions

import de.codecentric.hikaku.endpoints.PathParameter
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.method.HandlerMethod
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.kotlinFunction

internal fun HandlerMethod.pathParameter(): Set<PathParameter> {
    val method = this.method.kotlinFunction ?: return emptySet()

    return method.parameters
            .filter { it.annotations.filterIsInstance<PathVariable>().any() }
            .map { extractPathParameter(it) }
            .toSet()
}

private fun extractPathParameter(it: KParameter): PathParameter {
    val requestParam = it.annotations.find { it is PathVariable } as PathVariable
    val parameterName = extractPathParameterName(requestParam, it)

    return PathParameter(parameterName)
}

private fun extractPathParameterName(requestParam: PathVariable, it: KParameter): String {
    if (requestParam.value.isNotBlank() && requestParam.name.isNotBlank()) {
        throw IllegalStateException("Both 'value' and 'name' attribute are provided for path parameter '${it.name}'. Only one is permitted.")
    }

    return when {
        requestParam.value.isNotBlank() -> requestParam.value
        requestParam.name.isNotBlank() -> requestParam.name
        else -> it.name ?: ""
    }
}