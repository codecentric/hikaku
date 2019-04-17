package test.micronaut.path.onlycontrollerannotation.post

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/todos")
class OnlyControllerAnnotationWithPostPathTestController {

    @Post
    fun todos() { }
}