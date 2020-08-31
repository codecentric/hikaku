package test.micronaut.headerparameters.optional

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header

@Controller("/todos")
@Suppress("UNUSED_PARAMETER")
class HeaderParameterTestController {

    @Get
    fun todos(@Header("allow-cache", defaultValue = "true") otherName: String) { }
}
