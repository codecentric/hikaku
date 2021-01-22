package test.jaxrs.produces.multiplemediatypesonclass

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
@Produces("application/json", "application/xml")
class MultipleMediaTypesOnClass {

    @GET
    fun todo() = Todo()
}
