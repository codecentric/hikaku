package test.micronaut.produces.onfunction.onlyproduces.multiplemediatypes

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import test.micronaut.Todo

@Controller("/todos")
class ProducesMultipleMediaTypesTestController {

    @Get
    @Produces("text/plain", "application/xml")
    fun todos() = Todo()
}