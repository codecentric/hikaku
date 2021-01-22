package test.jaxrs.produces.singlemediatypeonclass

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
@Produces("application/json")
class ProducesOnClass {

    @GET
    fun todo() = Todo()
}
