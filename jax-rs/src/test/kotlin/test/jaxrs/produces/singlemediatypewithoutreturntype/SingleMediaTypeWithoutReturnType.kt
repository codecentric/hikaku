package test.jaxrs.produces.singlemediatypewithoutreturntype

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces

@Path("/todos")
class SingleMediaTypeWithoutReturnType {

    @GET
    @Produces("application/json")
    fun todo() { }
}
