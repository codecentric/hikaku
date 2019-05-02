package test.micronaut.produces.default

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import test.micronaut.Todo

@Controller("/todos")
class ProducesDefaultMediaTypeTestController {

    @Get
    fun todos() = Todo()
}