package test.micronaut.produces.onfunction.producesoverridescontroller.multiplemediatypes

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import test.micronaut.Todo

@Controller("/todos", produces = ["text/plain", "application/xml"])
class ProducesMultipleMediaTypesTestController {

    @Get
    @Produces("application/json", "application/pdf")
    fun todos() = Todo()
}