package test.jaxrs.produces.singlemediatypewithoutreturntype

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("/todos")
class SingleMediaTypeWithoutReturnType {

    @GET
    @Produces("application/json")
    fun todo() { }
}