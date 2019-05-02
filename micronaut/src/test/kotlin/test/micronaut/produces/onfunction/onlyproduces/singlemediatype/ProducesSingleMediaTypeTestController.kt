package test.micronaut.produces.onfunction.onlyproduces.singlemediatype

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import test.micronaut.Todo

@Controller("/todos")
class ProducesSingleMediaTypeTestController {

    @Get
    @Produces("text/plain")
    fun todos() = Todo()
}