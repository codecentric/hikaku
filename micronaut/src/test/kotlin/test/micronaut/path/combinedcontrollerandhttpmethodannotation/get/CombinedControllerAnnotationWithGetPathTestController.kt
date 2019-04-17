package test.micronaut.path.combinedcontrollerandhttpmethodannotation.get

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todo")
class CombinedControllerAnnotationWithGetPathTestController {

    @Get("/list")
    fun todos() { }
}