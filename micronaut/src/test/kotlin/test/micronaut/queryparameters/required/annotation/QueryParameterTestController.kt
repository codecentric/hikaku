package test.micronaut.queryparameters.required.annotation

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/todos")
@Suppress("UNUSED_PARAMETER")
class QueryParameterTestController {

    @Get
    fun todos(@QueryValue("filter") filter: String) { }
}
