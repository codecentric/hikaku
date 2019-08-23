package test.micronaut.path.onlycontrollerannotation.options

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Options

@Controller("/todos")
class OnlyControllerAnnotationWithOptionsPathTestController {

    @Options
    fun todos() { }
}