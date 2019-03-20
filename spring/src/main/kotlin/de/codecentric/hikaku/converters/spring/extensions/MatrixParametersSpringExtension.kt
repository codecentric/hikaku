package de.codecentric.hikaku.converters.spring.extensions

import de.codecentric.hikaku.endpoints.MatrixParameter
import org.springframework.web.bind.annotation.MatrixVariable
import org.springframework.web.bind.annotation.ValueConstants
import org.springframework.web.method.HandlerMethod
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.kotlinFunction

internal fun HandlerMethod.hikakuMatrixParameters(): Set<MatrixParameter> {
    val method = this.method.kotlinFunction ?: return emptySet()

    return method.parameters
            .filter { it.annotations.filterIsInstance<MatrixVariable>().any() }
            .map { extractMatrixParameter(it) }
            .toSet()
}

private fun extractMatrixParameter(it: KParameter): MatrixParameter {
    val matrixParameter = it.annotations.find { it is MatrixVariable } as MatrixVariable
    val parameterName = extractMatrixParameterName(matrixParameter, it)
    val isRequired = isMatrixParameterRequired(matrixParameter)

    return MatrixParameter(parameterName, isRequired)
}

private fun isMatrixParameterRequired(matrixParameter: MatrixVariable): Boolean {
    if (matrixParameter.defaultValue == ValueConstants.DEFAULT_NONE) {
        return matrixParameter.required
    }

    return false
}

private fun extractMatrixParameterName(matrixParameter: MatrixVariable, it: KParameter): String {
    if (matrixParameter.value.isNotBlank() && matrixParameter.name.isNotBlank()) {
        throw IllegalStateException("Both 'value' and 'name' attribute are provided for matrix parameter '${it.name}'. Only one is permitted.")
    }

    return when {
        matrixParameter.value.isNotBlank() -> matrixParameter.value
        matrixParameter.name.isNotBlank() -> matrixParameter.name
        else -> it.name ?: ""
    }
}