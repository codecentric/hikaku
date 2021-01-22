package test.jaxrs.path.nopathonclass

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

class NoPathAnnotationOnclass {

    @GET
    @Path("/todos")
    fun todo() { }
}
