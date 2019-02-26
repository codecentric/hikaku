package de.codecentric.hikaku.converters.openapi.extensions

import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.HttpMethod.POST
import de.codecentric.hikaku.endpoints.HttpMethod.PATCH
import de.codecentric.hikaku.endpoints.HttpMethod.DELETE
import de.codecentric.hikaku.endpoints.HttpMethod.PUT
import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS
import de.codecentric.hikaku.endpoints.HttpMethod.HEAD
import de.codecentric.hikaku.endpoints.HttpMethod.TRACE

internal fun PathItem.httpMethods() = mapOf<HttpMethod, Operation?>(
        GET to get,
        POST to post,
        PATCH to patch,
        DELETE to delete,
        PUT to put,
        OPTIONS to options,
        HEAD to head,
        TRACE to trace
).filterValues { it != null }