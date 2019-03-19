package test.jaxrs.produces.singlemediatypeonfunction

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
class ProducesOnFunction {

    @GET
    @Produces("application/json")
    fun todo() = Todo()
}