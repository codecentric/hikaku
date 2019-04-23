package test.micronaut.queryparameter.optional

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/todos")
class QueryParameterTestController {

    @Get
    fun todos(@QueryValue("filter", defaultValue = "all") filter: String) { }
}