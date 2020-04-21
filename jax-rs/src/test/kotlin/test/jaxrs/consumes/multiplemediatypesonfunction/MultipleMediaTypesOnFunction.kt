package test.jaxrs.consumes.multiplemediatypesonfunction

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
class MultipleMediaTypesOnFunction {

    @GET
    @Consumes("application/json", "application/xml")
    @Suppress("UNUSED_PARAMETER")
    fun todo(todo: Todo) { }
}
