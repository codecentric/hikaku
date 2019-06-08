package test.micronaut.deprecation.none

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todos", produces = ["text/plain", "application/xml"])
class NoDeprecation {

    @Get
    fun todo() { }
}