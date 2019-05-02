package test.micronaut.produces.onclass.producesoverridescontroller.singlemediatype

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import test.micronaut.Todo


@Produces("application/xml")
@Controller("/todos", produces = ["text/plain"])
class ProducesSingleMediaTypeTestController {

    @Get
    fun todos() = Todo()
}