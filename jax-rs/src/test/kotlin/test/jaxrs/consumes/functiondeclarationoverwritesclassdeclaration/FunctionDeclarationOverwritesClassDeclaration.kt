package test.jaxrs.consumes.functiondeclarationoverwritesclassdeclaration

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path

data class Todo(val description: String = "")

@Path("/todos")
@Consumes("application/xml")
@Suppress("UNUSED_PARAMETER")
class FunctionDeclarationOverwritesClassDeclaration {

    @GET
    @Consumes("application/json", "text/plain")
    fun todo(todo: Todo) { }
}
