package test.micronaut.consumes.onfunction.consumesoverridescontroller.singlemediatype

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import test.micronaut.Todo


@Controller("/todos", consumes = ["text/plain"])
@Suppress("UNUSED_PARAMETER")
class ConsumesSingleMediaTypeTestController {

    @Post
    @Consumes("application/xml")
    fun todos(@Body todo: Todo) { }
}
