package test.jaxrs.path.simplepath

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/todos")
class SimplePath {

    @GET
    fun todo() { }
}