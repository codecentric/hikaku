package test.micronaut.path.onlycontrollerannotation.delete

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete

@Controller("/todos")
class OnlyControllerAnnotationWithDeletePathTestController {

    @Delete
    fun todos() { }
}