package test.jaxrs.consumes.singlemediatypeonfunction

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class ProducesOnFunction {

    @GET
    @Consumes("application/json")
    fun todo(todo: Todo) { }
}
