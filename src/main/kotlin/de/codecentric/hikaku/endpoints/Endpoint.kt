package de.codecentric.hikaku.endpoints

import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS

data class Endpoint(
        val path: String = "",
        val httpMethod: HttpMethod = OPTIONS,
        val queryParameters: Set<QueryParameter> = emptySet(),
        val pathParameters: Set<PathParameter> = emptySet(),
        val headerParameters: Set<HeaderParameter> = emptySet()
)