package test.micronaut.path.onlycontrollerannotation.head

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Head

@Controller("/todos")
class OnlyControllerAnnotationWithHeadPathTestController {

    @Head
    fun todos() { }
}