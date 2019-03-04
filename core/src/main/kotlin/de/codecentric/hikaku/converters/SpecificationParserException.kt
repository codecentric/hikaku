package de.codecentric.hikaku.converters

class SpecificationParserException(message: String? = null, cause: Throwable? = null) : Throwable(message, cause) {
    companion object {
        operator fun invoke(throwable: Throwable) = SpecificationParserException(throwable.message, throwable)
    }
}