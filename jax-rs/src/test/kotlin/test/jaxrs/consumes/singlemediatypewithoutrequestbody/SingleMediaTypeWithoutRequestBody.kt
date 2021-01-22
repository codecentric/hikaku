package test.jaxrs.consumes.singlemediatypewithoutrequestbody

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/todos")
class SingleMediaTypeWithoutRequestBody {

    @GET
    @Consumes("application/json")
    fun todo() { }
}
