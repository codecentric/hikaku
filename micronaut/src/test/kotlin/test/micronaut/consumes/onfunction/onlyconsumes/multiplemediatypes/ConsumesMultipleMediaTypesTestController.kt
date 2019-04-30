package test.micronaut.consumes.onfunction.onlyconsumes.multiplemediatypes

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo

@Controller("/todos", consumes = ["text/plain", "application/xml"])
class ConsumesMultipleMediaTypesTestController {

    @Post
    fun todos(@Body todo: Todo) { }
}