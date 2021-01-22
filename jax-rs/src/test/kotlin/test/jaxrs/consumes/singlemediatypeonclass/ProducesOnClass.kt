package test.jaxrs.consumes.singlemediatypeonclass

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Consumes("application/json")
@Suppress("UNUSED_PARAMETER")
class ProducesOnClass {

    @GET
    fun todo(todo: Todo) { }
}
