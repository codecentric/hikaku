package test.jaxrs.path.nopathonclass

import javax.ws.rs.GET
import javax.ws.rs.Path

class NoPathAnnotationOnclass {

    @GET
    @Path("/todos")
    fun todo() { }
}