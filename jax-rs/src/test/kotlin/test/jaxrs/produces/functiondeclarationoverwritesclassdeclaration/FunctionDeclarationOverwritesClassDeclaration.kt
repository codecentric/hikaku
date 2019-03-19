package test.jaxrs.produces.functiondeclarationoverwritesclassdeclaration

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
@Produces("application/xml")
class FunctionDeclarationOverwritesClassDeclaration {

    @GET
    @Produces("application/json", "text/plain")
    fun todo() = Todo()
}