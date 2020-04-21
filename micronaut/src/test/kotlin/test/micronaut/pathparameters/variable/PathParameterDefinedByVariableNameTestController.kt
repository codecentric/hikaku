package test.micronaut.pathparameters.variable

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todos/{id}")
class PathParameterDefinedByVariableNameTestController {

    @Get
    @Suppress("UNUSED_PARAMETER")
    fun todos(id: String) { }
}
