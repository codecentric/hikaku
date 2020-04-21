package test.jaxrs.pathparameters.onfunction

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path("/todos")
class PathParameterOnFunction {

    @GET
    @Path("/{id}")
    @Suppress("UNUSED_PARAMETER")
    fun todo(@PathParam("id") id: String) { }
}
