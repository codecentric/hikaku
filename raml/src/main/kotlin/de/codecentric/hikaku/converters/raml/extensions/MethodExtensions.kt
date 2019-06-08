package de.codecentric.hikaku.converters.raml.extensions

import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.QueryParameter
import org.raml.v2.api.model.v10.methods.Method

internal fun Method.hikakuHttpMethod() = HttpMethod.valueOf(this.method().toUpperCase())

internal fun Method.hikakuQueryParameters(): Set<QueryParameter> {
    return this.queryParameters()
            .map {
                QueryParameter(it.name(), it.required())
            }
            .toSet()
}

internal fun Method.hikakuHeaderParameters(): Set<HeaderParameter> {
    return this.headers()
            .map {
                HeaderParameter(it.name(), it.required())
            }
            .toSet()
}

internal fun Method.requestMediaTypes(): Set<String> {
    return this.body().map {
        it.name()
    }
    .toSet()
}

internal fun Method.responseMediaTypes(): Set<String> {
    return this.responses().flatMap {response ->
        response.body().map { it.name() }
    }
            .toSet()
}

internal fun Method.isEndpointDeprecated() =
        this.annotations().any { i -> i.annotation().name() == "deprecated" }
                || checkNotNull(this.resource()).annotations().any { i -> i.annotation().name() == "deprecated" }