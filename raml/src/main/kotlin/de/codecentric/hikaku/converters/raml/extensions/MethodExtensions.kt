package de.codecentric.hikaku.converters.raml.extensions

import de.codecentric.hikaku.endpoints.HttpMethod
import org.raml.v2.api.model.v10.methods.Method

internal fun Method.httpMethod(): HttpMethod {
    return HttpMethod.valueOf(this.method().toUpperCase())
}