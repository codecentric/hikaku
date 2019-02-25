package de.codecentric.hikaku.endpoints

import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS

/**
 * A single [Endpoint] containing all information. Each [Endpoint] consists of exactly one path in combination with exactly one [HttpMethod].
 * If a REST endpoint supports multiple [HttpMethod]s, this will result in multiple [Endpoint] instances.
 * @param path The path excluding a base path. **Example:** `/todos`
 * @param produces Supported media types for the response.
 * @param consumes Supported media types for the request.
 */
data class Endpoint(
        val path: String = "",
        val httpMethod: HttpMethod = OPTIONS,
        val queryParameters: Set<QueryParameter> = emptySet(),
        val pathParameters: Set<PathParameter> = emptySet(),
        val headerParameters: Set<HeaderParameter> = emptySet(),
        val produces: Set<String> = emptySet(),
        val consumes: Set<String> = emptySet()
)