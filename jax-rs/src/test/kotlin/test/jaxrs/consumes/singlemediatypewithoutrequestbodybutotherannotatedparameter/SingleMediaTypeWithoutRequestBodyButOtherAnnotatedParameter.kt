package test.jaxrs.consumes.singlemediatypewithoutrequestbodybutotherannotatedparameter

import javax.ws.rs.*

@Path("/todos")
class SingleMediaTypeWithoutRequestBodyButOtherAnnotatedParameter {

    @GET
    @Consumes("application/json")
    fun todo(@Encoded filter: String) { }
}