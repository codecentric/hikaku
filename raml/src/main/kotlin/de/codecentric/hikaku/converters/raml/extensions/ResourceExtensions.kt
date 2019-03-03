package de.codecentric.hikaku.converters.raml.extensions

import de.codecentric.hikaku.endpoints.PathParameter
import org.raml.v2.api.model.v10.resources.Resource

fun Resource.hikakuPathParameters(): Set<PathParameter> {
    return this.uriParameters()
            .map {
                PathParameter(it.name())
            }
            .toSet()
}