package test.jaxrs.consumes.singlemediatypeonclass

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Consumes("application/json")
@Suppress("UNUSED_PARAMETER")
class ProducesOnClass {

    @GET
    fun todo(todo: Todo) { }
}
