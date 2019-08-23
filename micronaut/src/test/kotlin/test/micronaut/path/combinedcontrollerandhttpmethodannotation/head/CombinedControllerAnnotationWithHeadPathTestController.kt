package test.micronaut.path.combinedcontrollerandhttpmethodannotation.head

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Head

@Controller("/todo")
class CombinedControllerAnnotationWithHeadPathTestController {

    @Head("/list")
    fun todos() { }
}