package test.micronaut.path.combinedcontrollerandhttpmethodannotation.delete

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete

@Controller("/todo")
class CombinedControllerAnnotationWithDeletePathTestController {

    @Delete("/list")
    fun todos() { }
}