package test.jaxrs.consumes.multiplemediatypesonfunction

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class MultipleMediaTypesOnFunction {

    @GET
    @Consumes("application/json", "application/xml")
    fun todo(todo: Todo) { }
}
