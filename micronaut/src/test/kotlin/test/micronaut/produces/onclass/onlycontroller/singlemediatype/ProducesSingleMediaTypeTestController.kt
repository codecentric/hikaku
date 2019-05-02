package test.micronaut.produces.onclass.onlycontroller.singlemediatype

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import test.micronaut.Todo


@Controller("/todos", produces = ["text/plain"])
class ProducesSingleMediaTypeTestController {

    @Get
    fun todos() = Todo()
}