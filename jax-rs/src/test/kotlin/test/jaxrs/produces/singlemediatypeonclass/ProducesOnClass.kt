package test.jaxrs.produces.singlemediatypeonclass

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
@Produces("application/json")
class ProducesOnClass {

    @GET
    fun todo() = Todo()
}