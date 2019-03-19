package test.jaxrs.produces.multiplemediatypesonfunction

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
class MultipleMediaTypesOnFunction {

    @GET
    @Produces("application/json", "application/xml")
    fun todo() = Todo()
}