package test.micronaut.path.nohttpmethodannotation

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todos")
class NoHttpMethodAnnotationTestController {

    fun todos() { }
}