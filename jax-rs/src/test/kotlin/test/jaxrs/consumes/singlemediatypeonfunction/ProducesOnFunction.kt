package test.jaxrs.consumes.singlemediatypeonfunction

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class ProducesOnFunction {

    @GET
    @Consumes("application/json")
    fun todo(todo: Todo) { }
}
