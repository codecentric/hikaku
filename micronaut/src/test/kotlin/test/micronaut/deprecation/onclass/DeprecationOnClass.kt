package test.micronaut.deprecation.onclass

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todos")
@Deprecated("Test")
class DeprecationOnClass {

    @Get
    fun todo() { }
}