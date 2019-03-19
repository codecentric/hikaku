package test.jaxrs.produces.multiplemediatypesonclass

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
@Produces("application/json", "application/xml")
class MultipleMediaTypesOnClass {

    @GET
    fun todo() = Todo()
}