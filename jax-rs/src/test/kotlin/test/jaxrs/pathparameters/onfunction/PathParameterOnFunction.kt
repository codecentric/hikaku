package test.jaxrs.pathparameters.onfunction

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class PathParameterOnFunction {

    @GET
    @Path("/{id}")
    fun todo(@PathParam("id") id: String) { }
}
