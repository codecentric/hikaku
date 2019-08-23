package test.jaxrs.matrixparameters.onfunction

import javax.ws.rs.GET
import javax.ws.rs.MatrixParam
import javax.ws.rs.Path

@Path("/todos")
class MatrixParameterOnFunction {

    @GET
    fun todo(@MatrixParam("tag") tag: String) { }
}