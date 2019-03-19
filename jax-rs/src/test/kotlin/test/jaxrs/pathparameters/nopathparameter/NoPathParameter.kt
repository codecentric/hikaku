package test.jaxrs.pathparameters.nopathparameter

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/todos")
class NoPathParameter {

    @GET
    @Path("/{id}")
    fun todo() { }
}