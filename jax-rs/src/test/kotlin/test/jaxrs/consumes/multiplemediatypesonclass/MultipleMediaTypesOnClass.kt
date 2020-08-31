package test.jaxrs.consumes.multiplemediatypesonclass

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Consumes("application/json", "application/xml")
@Suppress("UNUSED_PARAMETER")
class MultipleMediaTypesOnClass {

    @GET
    fun todo(todo: Todo) {}
}
