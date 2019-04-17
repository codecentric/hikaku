package test.micronaut.path.onlycontrollerannotation.patch

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch

@Controller("/todos")
class OnlyControllerAnnotationWithPatchPathTestController {

    @Patch
    fun todos() { }
}