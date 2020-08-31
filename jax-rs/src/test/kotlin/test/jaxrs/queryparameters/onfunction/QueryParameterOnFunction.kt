package test.jaxrs.queryparameters.onfunction

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class QueryParameterOnFunction {

    @GET
    fun todo(@QueryParam("filter") filter: String) { }
}
