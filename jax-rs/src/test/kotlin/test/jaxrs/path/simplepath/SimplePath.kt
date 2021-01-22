package test.jaxrs.path.simplepath

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/todos")
class SimplePath {

    @GET
    fun todo() { }
}
