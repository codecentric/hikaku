package test.jaxrs.deprecation.onfunction

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/todos")
class DeprecationOnFunction {

    @GET
    @Deprecated("Test")
    fun todo() { }
}