package test.jaxrs.consumes.functiondeclarationoverwritesclassdeclaration

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Consumes("application/xml")
@Suppress("UNUSED_PARAMETER")
class FunctionDeclarationOverwritesClassDeclaration {

    @GET
    @Consumes("application/json", "text/plain")
    fun todo(todo: Todo) { }
}
