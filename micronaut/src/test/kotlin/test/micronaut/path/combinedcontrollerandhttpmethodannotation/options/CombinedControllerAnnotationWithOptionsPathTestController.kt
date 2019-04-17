package test.micronaut.path.combinedcontrollerandhttpmethodannotation.options

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Options

@Controller("/todo")
class CombinedControllerAnnotationWithOptionsPathTestController {

    @Options("/list")
    fun todos() { }
}