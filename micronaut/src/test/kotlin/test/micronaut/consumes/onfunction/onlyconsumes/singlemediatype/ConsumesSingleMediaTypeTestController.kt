package test.micronaut.consumes.onfunction.onlyconsumes.singlemediatype

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo


@Controller("/todos", consumes = ["text/plain"])
class ConsumesSingleMediaTypeTestController {

    @Post
    @Suppress("UNUSED_PARAMETER")
    fun todos(@Body todo: Todo) { }
}
