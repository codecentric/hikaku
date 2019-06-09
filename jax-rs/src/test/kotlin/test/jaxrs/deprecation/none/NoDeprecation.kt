package test.jaxrs.deprecation.none

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/todos")
class NoDeprecation {

    @GET
    fun todo() { }
}