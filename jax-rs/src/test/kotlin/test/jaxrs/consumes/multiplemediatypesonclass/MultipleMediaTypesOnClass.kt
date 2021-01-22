package test.jaxrs.consumes.multiplemediatypesonclass

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Consumes("application/json", "application/xml")
@Suppress("UNUSED_PARAMETER")
class MultipleMediaTypesOnClass {

    @GET
    fun todo(todo: Todo) {}
}
