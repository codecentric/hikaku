package de.codecentric.hikaku.converters

/**
 * Is thrown in case an [EndpointConverter] is not able to perform the conversion.
 */
class EndpointConverterException(message: String? = null, cause: Throwable? = null) : Throwable(message, cause) {
    constructor(throwable: Throwable): this(throwable.message, throwable)
}