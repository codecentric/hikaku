package test.jaxrs.deprecation.onclass

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/todos")
@Deprecated("Test")
class DeprecationOnClass {

    @GET
    fun todo() { }
}