package test.micronaut.path.nohttpmethodannotation

import io.micronaut.http.annotation.Controller

@Controller("/todos")
class NoHttpMethodAnnotationTestController {

    fun todos() { }
}