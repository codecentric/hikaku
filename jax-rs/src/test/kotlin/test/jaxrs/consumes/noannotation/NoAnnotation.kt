package test.jaxrs.consumes.noannotation

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class NoAnnotation {

    @GET
    fun todo(todo: Todo) { }
}
