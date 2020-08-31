package test.micronaut.consumes.default

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo

@Controller("/todos")
@Suppress("UNUSED_PARAMETER")
class ConsumesDefaultMediaTypeTestController {

    @Post
    fun todos(@Body todo: Todo) { }
}
