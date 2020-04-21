package test.jaxrs.consumes.noannotation

import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
class NoAnnotation {

    @GET
    @Suppress("UNUSED_PARAMETER")
    fun todo(todo: Todo) { }
}
