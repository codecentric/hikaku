package de.codecentric.hikaku.converters.openapi.extensions

import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.parameters.RequestBody
import io.swagger.v3.oas.models.responses.ApiResponse


internal val ApiResponse.referencedSchema
    get() = `$ref`

internal val RequestBody.referencedSchema
    get() = `$ref`

internal val Parameter.referencedSchema
    get() = `$ref`