package test.micronaut.headerparameters.required

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header

@Controller("/todos")
class HeaderParameterTestController {

    @Get
    @Suppress("UNUSED_PARAMETER")
    fun todos(@Header("allow-cache") otherName: String) { }
}
