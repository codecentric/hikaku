package test.micronaut.pathparameters.annotation.value

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Controller("/todos/{id}")
@Suppress("UNUSED_PARAMETER")
class PathParameterDefinedByAnnotationTestController {

    @Get
    fun todos(@PathVariable("id") otherName: String) { }
}
