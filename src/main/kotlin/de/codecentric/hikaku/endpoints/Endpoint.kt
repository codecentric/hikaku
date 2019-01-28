package de.codecentric.hikaku.endpoints

import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS


data class Endpoint(
        val path: String = "",
        val httpMethod: HttpMethod = OPTIONS,
        val queryParameter: Set<QueryParameter> = emptySet(),
        val pathParameter: Set<PathParameter> = emptySet()
)