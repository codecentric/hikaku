package test.jaxrs.produces.functiondeclarationoverwritesclassdeclaration

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces

data class Todo(val description: String = "")

@Path("/todos")
@Produces("application/xml")
class FunctionDeclarationOverwritesClassDeclaration {

    @GET
    @Produces("application/json", "text/plain")
    fun todo() = Todo()
}
