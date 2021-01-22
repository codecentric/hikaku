package test.jaxrs.queryparameters.onfunction

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class QueryParameterOnFunction {

    @GET
    fun todo(@QueryParam("filter") filter: String) { }
}
