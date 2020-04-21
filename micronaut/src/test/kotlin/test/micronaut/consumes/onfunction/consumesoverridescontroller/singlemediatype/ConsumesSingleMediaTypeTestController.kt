package test.micronaut.consumes.onfunction.consumesoverridescontroller.singlemediatype

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo


@Controller("/todos", consumes = ["text/plain"])
class ConsumesSingleMediaTypeTestController {

    @Post
    @Consumes("application/xml")
    @Suppress("UNUSED_PARAMETER")
    fun todos(@Body todo: Todo) { }
}
