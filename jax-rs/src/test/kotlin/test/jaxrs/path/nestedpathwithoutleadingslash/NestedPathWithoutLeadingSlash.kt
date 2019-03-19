package test.jaxrs.path.nestedpathwithoutleadingslash

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/todo")
class NestedPath {

    @GET
    @Path("list")
    fun todo() { }
}