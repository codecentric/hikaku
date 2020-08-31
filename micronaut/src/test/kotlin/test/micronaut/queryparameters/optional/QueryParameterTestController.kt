package test.micronaut.queryparameters.optional

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/todos")
@Suppress("UNUSED_PARAMETER")
class QueryParameterTestController {

    @Get
    fun todos(@QueryValue("filter", defaultValue = "all") filter: String) { }
}
