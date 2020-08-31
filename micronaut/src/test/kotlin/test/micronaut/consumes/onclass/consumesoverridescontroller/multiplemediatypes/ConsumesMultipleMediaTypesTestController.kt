package test.micronaut.consumes.onclass.consumesoverridescontroller.multiplemediatypes

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo

@Controller("/todos", consumes = ["text/plain", "application/xml"])
@Consumes("application/json", "application/pdf")
@Suppress("UNUSED_PARAMETER")
class ConsumesMultipleMediaTypesTestController {

    @Post
    fun todos(@Body todo: Todo) { }
}
