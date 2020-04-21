package test.micronaut.queryparameters.required.annotation

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/todos")
class QueryParameterTestController {

    @Get
    @Suppress("UNUSED_PARAMETER")
    fun todos(@QueryValue("filter") filter: String) { }
}
