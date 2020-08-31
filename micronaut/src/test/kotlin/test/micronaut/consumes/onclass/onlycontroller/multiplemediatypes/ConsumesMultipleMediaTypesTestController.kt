package test.micronaut.consumes.onclass.onlycontroller.multiplemediatypes

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo

@Controller("/todos", consumes = ["text/plain", "application/xml"])
@Suppress("UNUSED_PARAMETER")
class ConsumesMultipleMediaTypesTestController {

    @Post
    fun todos(@Body todo: Todo) { }
}
