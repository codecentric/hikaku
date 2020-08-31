package test.micronaut.consumes.onclass.onlycontroller.singlemediatype

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo


@Controller("/todos", consumes = ["text/plain"])
@Suppress("UNUSED_PARAMETER")
class ConsumesSingleMediaTypeTestController {

    @Post
    fun todos(@Body todo: Todo) { }
}
