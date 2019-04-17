package test.micronaut.path.onlycontrollerannotation.get

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todos")
class OnlyControllerAnnotationWithGetPathTestController {

    @Get
    fun todos() { }
}