package test.micronaut.path.combinedcontrollerandhttpmethodannotation.patch

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch

@Controller("/todo")
class CombinedControllerAnnotationWithPatchPathTestController {

    @Patch("/list")
    fun todos() { }
}