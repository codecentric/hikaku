package test.jaxrs.headerparameters.onfunction

import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.Path

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class HeaderParameterOnFunction {

    @GET
    fun todo(@HeaderParam("allow-cache") allowCache: String) { }
}
