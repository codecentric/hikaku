package test.jaxrs.deprecation.none

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/todos")
class NoDeprecation {

    @GET
    fun todo() { }
}
