package test.micronaut.queryparameters.required.withoutannotation

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todos")
class QueryParameterTestController {

    @Get
    fun todos(filter: String) { }
}