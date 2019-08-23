package test.micronaut.path.combinedcontrollerandhttpmethodannotation.post

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/todo")
class CombinedControllerAnnotationWithPostPathTestController {

    @Post("/list")
    fun todos() { }
}