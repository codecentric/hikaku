package test.jaxrs.consumes.noannotation

import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class NoAnnotation {

    @GET
    fun todo(todo: Todo) { }
}
