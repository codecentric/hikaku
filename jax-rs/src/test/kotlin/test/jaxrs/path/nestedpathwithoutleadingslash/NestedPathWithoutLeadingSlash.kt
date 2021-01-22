package test.jaxrs.path.nestedpathwithoutleadingslash

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/todo")
class NestedPath {

    @GET
    @Path("list")
    fun todo() { }
}
