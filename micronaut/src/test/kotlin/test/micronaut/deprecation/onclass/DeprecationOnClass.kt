package test.micronaut.deprecation.onclass

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todos", produces = ["text/plain", "application/xml"])
@Deprecated("Test")
class DeprecationOnClass {

    @Get
    fun todo() { }
}