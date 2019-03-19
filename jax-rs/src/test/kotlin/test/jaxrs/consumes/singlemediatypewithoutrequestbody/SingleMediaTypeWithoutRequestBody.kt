package test.jaxrs.consumes.singlemediatypewithoutrequestbody

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/todos")
class SingleMediaTypeWithoutRequestBody {

    @GET
    @Consumes("application/json")
    fun todo() { }
}