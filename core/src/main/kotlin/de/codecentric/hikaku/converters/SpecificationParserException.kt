package de.codecentric.hikaku.converters

class SpecificationParserException(message: String? = null, cause: Throwable? = null) : Throwable(message, cause) {
    constructor(throwable: Throwable): this(throwable.message, throwable)
}