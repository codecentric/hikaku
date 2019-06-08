package test.micronaut.deprecation.onfunction

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todos", produces = ["text/plain", "application/xml"])
class DeprecationOnFunction {

    @Get
    @Deprecated("Test")
    fun todo() { }
}