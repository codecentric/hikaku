package de.codecentric.hikaku.converters.raml.extensions

import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.QueryParameter
import org.raml.v2.api.model.v10.methods.Method

internal fun Method.hikakuHttpMethod() = HttpMethod.valueOf(this.method().toUpperCase())

internal fun Method.hikakuQueryParameters() = this.queryParameters()
        .map {
            QueryParameter(it.name(), it.required())
        }
        .toSet()

internal fun Method.hikakuHeaderParameters() = this.headers().map {
            HeaderParameter(it.name(), it.required())
        }
        .toSet()