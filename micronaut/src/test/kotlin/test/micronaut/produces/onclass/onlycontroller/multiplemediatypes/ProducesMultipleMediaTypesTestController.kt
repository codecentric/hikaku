package test.micronaut.produces.onclass.onlycontroller.multiplemediatypes

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import test.micronaut.Todo

@Controller("/todos", produces = ["text/plain", "application/xml"])
class ProducesMultipleMediaTypesTestController {

    @Get
    fun todos() = Todo()
}