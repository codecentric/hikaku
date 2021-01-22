package test.jaxrs.pathparameters.nopathparameter

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/todos")
class NoPathParameter {

    @GET
    @Path("/{id}")
    fun todo() { }
}
