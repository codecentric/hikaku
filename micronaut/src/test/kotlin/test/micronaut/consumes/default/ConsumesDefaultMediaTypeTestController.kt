package test.micronaut.consumes.default

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo

@Controller("/todos")
class ConsumesDefaultMediaTypeTestController {

    @Post
    @Suppress("UNUSED_PARAMETER")
    fun todos(@Body todo: Todo) { }
}
