package test.micronaut.path.combinedcontrollerandhttpmethodannotation.put

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Put

@Controller("/todo")
class CombinedControllerAnnotationWithPutPathTestController {

    @Put("/list")
    fun todos() { }
}