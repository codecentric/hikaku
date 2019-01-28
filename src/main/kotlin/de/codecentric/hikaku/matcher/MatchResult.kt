package de.codecentric.hikaku.matcher

data class MatchResult<T>(
        val relatesTo: String,
        val specificationValue: T,
        val implementationValue: T,
        val matchFunction: (T, T) -> Boolean = { _, _ -> specificationValue == implementationValue }
) {
    val matches = matchFunction(specificationValue, implementationValue)
}