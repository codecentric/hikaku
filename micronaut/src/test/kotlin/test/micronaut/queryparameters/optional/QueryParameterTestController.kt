package test.micronaut.queryparameters.optional

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/todos")
class QueryParameterTestController {

    @Get
    @Suppress("UNUSED_PARAMETER")
    fun todos(@QueryValue("filter", defaultValue = "all") filter: String) { }
}
