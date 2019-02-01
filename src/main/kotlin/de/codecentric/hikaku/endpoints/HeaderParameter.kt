package de.codecentric.hikaku.endpoints

data class HeaderParameter(
    val parameterName: String,
    val required: Boolean = false
)