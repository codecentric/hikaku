package test.jaxrs.deprecation.onfunction

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/todos")
class DeprecationOnFunction {

    @GET
    @Deprecated("Test")
    fun todo() { }
}
