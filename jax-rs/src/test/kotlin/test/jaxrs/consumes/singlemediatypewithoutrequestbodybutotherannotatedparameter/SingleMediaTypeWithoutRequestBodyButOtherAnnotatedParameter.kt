package test.jaxrs.consumes.singlemediatypewithoutrequestbodybutotherannotatedparameter

import javax.ws.rs.*

@Path("/todos")
@Suppress("UNUSED_PARAMETER")
class SingleMediaTypeWithoutRequestBodyButOtherAnnotatedParameter {

    @GET
    @Consumes("application/json")
    fun todo(@Encoded filter: String) { }
}
