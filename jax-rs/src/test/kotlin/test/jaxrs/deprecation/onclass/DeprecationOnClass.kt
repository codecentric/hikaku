package test.jaxrs.deprecation.onclass

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/todos")
@Deprecated("Test")
class DeprecationOnClass {

    @GET
    fun todo() { }
}
