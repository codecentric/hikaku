package test.micronaut.queryparameter.required.withoutannotation

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/todos")
class QueryParameterTestController {

    @Get
    fun todos(filter: String) { }
}