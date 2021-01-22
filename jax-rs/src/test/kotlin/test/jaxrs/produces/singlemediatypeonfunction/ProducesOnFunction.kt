package test.jaxrs.produces.singlemediatypeonfunction

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
class ProducesOnFunction {

    @GET
    @Produces("application/json")
    fun todo() = Todo()
}
