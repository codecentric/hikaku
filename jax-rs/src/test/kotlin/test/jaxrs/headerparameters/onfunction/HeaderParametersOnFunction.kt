package test.jaxrs.headerparameters.onfunction

import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path

@Path("/todos")
class HeaderParameterOnFunction {

    @GET
    @Suppress("UNUSED_PARAMETER")
    fun todo(@HeaderParam("allow-cache") allowCache: String) { }
}
