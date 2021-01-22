package test.jaxrs.path.simplepathwithoutleadingslash

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("todos")
class SimplePathWithoutLeadingSlash {

    @GET
    fun todo() { }
}
