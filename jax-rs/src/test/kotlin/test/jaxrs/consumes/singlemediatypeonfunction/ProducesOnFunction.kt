package test.jaxrs.consumes.singlemediatypeonfunction

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
class ProducesOnFunction {

    @GET
    @Consumes("application/json")
    @Suppress("UNUSED_PARAMETER")
    fun todo(todo: Todo) { }
}
