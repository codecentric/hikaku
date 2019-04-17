package test.micronaut.path.onlycontrollerannotation.put

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Put

@Controller("/todos")
class OnlyControllerAnnotationWithPutPathTestController {

    @Put
    fun todos() { }
}