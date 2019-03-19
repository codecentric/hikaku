package test.jaxrs.httpmethod.allmethods

import javax.ws.rs.*

@Path("/todos")
class AllHttpMethods {

    @GET
    fun getTodo() { }

    @DELETE
    fun deleteTodo() { }

    @POST
    fun postTodo() { }

    @PUT
    fun putTodos() { }

    @PATCH
    fun patchTodos() { }

    @OPTIONS
    fun optionsTodos() { }

    @HEAD
    fun headTodos() { }
}