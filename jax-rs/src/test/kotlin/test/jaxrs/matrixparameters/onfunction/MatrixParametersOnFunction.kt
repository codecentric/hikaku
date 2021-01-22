package test.jaxrs.matrixparameters.onfunction

import jakarta.ws.rs.GET
import jakarta.ws.rs.MatrixParam
import jakarta.ws.rs.Path

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class MatrixParameterOnFunction {

    @GET
    fun todo(@MatrixParam("tag") tag: String) { }
}
