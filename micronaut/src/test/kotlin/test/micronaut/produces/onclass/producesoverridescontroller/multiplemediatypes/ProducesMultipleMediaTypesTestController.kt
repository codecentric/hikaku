package test.micronaut.produces.onclass.producesoverridescontroller.multiplemediatypes

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import test.micronaut.Todo

@Controller("/todos", produces = ["text/plain", "application/xml"])
@Produces("application/json", "application/pdf")
class ProducesMultipleMediaTypesTestController {

    @Get
    fun todos() = Todo()
}