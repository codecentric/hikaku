package test.jaxrs.path.simplepathwithoutleadingslash

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("todos")
class SimplePathWithoutLeadingSlash {

    @GET
    fun todo() { }
}