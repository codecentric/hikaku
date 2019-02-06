package de.codecentric.hikaku.converter.spring.endpoints

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*

@SpringBootApplication
open class DummyApp

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassImplicitlyAddingAllHttpMethodsController {

    @RequestMapping
    fun getAllTodos() { }

    @GetMapping("/tags")
    fun getAllTags() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithExplicitGetMethodController {

    @RequestMapping(method = [GET])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithGetMappingController {

    @GetMapping
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithExplicitPostMethodController {

    @RequestMapping(method = [POST])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithPostMappingController {

    @PostMapping
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithExplicitPutMethodController {

    @RequestMapping(method = [PUT])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithPutMappingController {

    @PutMapping
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithExplicitDeleteMethodController {

    @RequestMapping(method = [DELETE])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithDeleteMappingController {

    @DeleteMapping
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithExplicitPatchMethodController {

    @RequestMapping(method = [PATCH])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithPatchMappingController {

    @PatchMapping
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithExplicitHeadMethodController {

    @RequestMapping(method = [HEAD])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithExplicitOptionsMethodController {

    @RequestMapping(method = [OPTIONS])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassWithExplicitTraceMethodController {

    @RequestMapping(method = [TRACE])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class MultipleExplicitMappingAnnotationsAreNotSupportedBySpringController {

    @GetMapping
    @PostMapping
    fun getAllTodos() { }
}

@RestController
open class RequestMappingOnlyOnFunctionController {

    @RequestMapping("/todos", method = [GET])
    fun getAllTodos() { }
}

@RestController
open class MappingOnlyOnFunctionController {

    @GetMapping("/todos")
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todo")
open class RequestMappingDefinedOnClassAndGetMappingOnFunctionController {

    @GetMapping("/list")
    fun getAllTodos() { }
}

@RestController
open class MultipleEndpointsDefinedOnGetMappingAnnotationController {

    @GetMapping(value = ["/todos", "/todo/list"])
    fun getAllTodos() { }
}

@RestController
open class MultipleEndpointsDefinedOnRequestMappingAnnotationController {

    @RequestMapping(value = ["/todos", "/todo/list"], method = [GET])
    fun getAllTodos() { }
}

@RestController
open class MultipleEndpointsAndMethodsDefinedOnRequestMappingAnnotationController {

    @RequestMapping(value = ["/todos", "/todo/list"], method = [GET, POST])
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingProvidingRegexForPathVariableController {

    @GetMapping("/{id:[0-9]+}")
    fun getSpecificTodo(@PathVariable(name = "id") variable: Int) { }
}

@RestController
@RequestMapping("/todos")
open class GetMappingProvidingRegexForPathVariableController {

    @GetMapping("/{id:[0-9]+}")
    fun getSpecificTodo(@PathVariable(name = "id") variable: Int) { }
}