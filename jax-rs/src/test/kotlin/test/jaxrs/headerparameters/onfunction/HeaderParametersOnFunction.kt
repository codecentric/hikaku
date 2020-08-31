package test.jaxrs.headerparameters.onfunction

import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class HeaderParameterOnFunction {

    @GET
    fun todo(@HeaderParam("allow-cache") allowCache: String) { }
}
