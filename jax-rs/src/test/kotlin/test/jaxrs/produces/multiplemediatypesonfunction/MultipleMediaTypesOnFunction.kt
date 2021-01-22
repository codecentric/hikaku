package test.jaxrs.produces.multiplemediatypesonfunction

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
class MultipleMediaTypesOnFunction {

    @GET
    @Produces("application/json", "application/xml")
    fun todo() = Todo()
}
