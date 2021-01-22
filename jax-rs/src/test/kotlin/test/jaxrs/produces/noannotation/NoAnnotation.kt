package test.jaxrs.produces.noannotation

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
class NoAnnotation {

    @GET
    fun todo() = Todo()
}
