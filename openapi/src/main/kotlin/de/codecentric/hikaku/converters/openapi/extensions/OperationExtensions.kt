package de.codecentric.hikaku.converters.openapi.extensions

import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.PathParameter
import de.codecentric.hikaku.endpoints.QueryParameter
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.parameters.QueryParameter as OpenApiQueryParameter
import io.swagger.v3.oas.models.parameters.PathParameter as OpenApiPathParameter
import io.swagger.v3.oas.models.parameters.HeaderParameter as OpenApiHeaderParameter

fun Operation?.hikakuQueryParameters(): Set<QueryParameter> {
    return this?.parameters
            ?.filter { it is OpenApiQueryParameter }
            ?.map { QueryParameter(it.name, it.required) }
            ?.toSet()
            .orEmpty()
}

fun Operation?.hikakuPathParameters(): Set<PathParameter> {
    return this?.parameters
            ?.filter { it is OpenApiPathParameter }
            ?.map { PathParameter(it.name) }
            ?.toSet()
            .orEmpty()
}

fun Operation?.hikakuHeaderParameters(): Set<HeaderParameter> {
    return this?.parameters
            ?.filter { it is OpenApiHeaderParameter }
            ?.map { HeaderParameter(it.name, it.required) }
            ?.toSet()
            .orEmpty()
}