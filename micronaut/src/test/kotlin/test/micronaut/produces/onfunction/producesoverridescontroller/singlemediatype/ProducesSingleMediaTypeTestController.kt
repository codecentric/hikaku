package test.micronaut.produces.onfunction.producesoverridescontroller.singlemediatype

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import test.micronaut.Todo


@Controller("/todos", produces = ["text/plain"])
class ProducesSingleMediaTypeTestController {

    @Get
    @Produces("application/xml")
    fun todos() = Todo()
}