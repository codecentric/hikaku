package test.jaxrs.consumes.multiplemediatypesonclass

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Consumes("application/json", "application/xml")
class MultipleMediaTypesOnClass {

    @GET
    @Suppress("UNUSED_PARAMETER")
    fun todo(todo: Todo) {}
}
