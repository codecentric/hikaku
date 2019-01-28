package de.codecentric.hikaku.endpoints

data class QueryParameter(
        val parameterName: String,
        val required: Boolean = false
)