package de.codecentric.hikaku.converter.spring.httpmethod

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*

@SpringBootApplication
open class DummyApp

@Controller
@RequestMapping("/todos", method = [GET])
open class RequestMappingDefinedOnClassWithExplicitGetMethodController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionWithExplicitGetMethodController {

    @RequestMapping("/todos", method = [GET])
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [POST])
open class RequestMappingDefinedOnClassWithExplicitPostMethodController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionWithExplicitPostMethodController {

    @RequestMapping("/todos", method = [POST])
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [HEAD])
open class RequestMappingDefinedOnClassWithExplicitHeadMethodController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionWithExplicitHeadMethodController {

    @RequestMapping("/todos", method = [HEAD])
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [PUT])
open class RequestMappingDefinedOnClassWithExplicitPutMethodController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionWithExplicitPutMethodController {

    @RequestMapping("/todos", method = [PUT])
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [PATCH])
open class RequestMappingDefinedOnClassWithExplicitPatchMethodController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionWithExplicitPatchMethodController {

    @RequestMapping("/todos", method = [PATCH])
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [DELETE])
open class RequestMappingDefinedOnClassWithExplicitDeleteMethodController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionWithExplicitDeleteMethodController {

    @RequestMapping("/todos", method = [DELETE])
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [TRACE])
open class RequestMappingDefinedOnClassWithExplicitTraceMethodController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionWithExplicitTraceMethodController {

    @RequestMapping("/todos", method = [TRACE])
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [OPTIONS])
open class RequestMappingDefinedOnClassWithExplicitOptionsMethodController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionWithExplicitOptionsMethodController {

    @RequestMapping("/todos", method = [OPTIONS])
    fun todos() { }
}

@Controller
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassNoHttpMethodDefinedController {

    @RequestMapping
    fun todos() { }
}

@Controller
open class RequestMappingDefinedOnFunctionNoHttpMethodDefinedController {

    @RequestMapping("/todos")
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [PUT])
open class RequestMappingDefinedOnClassAndFunctionWithDifferentHttpMethodsController {

    @RequestMapping(method = [PATCH])
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [GET])
open class RequestMappingDefinedOnClassAndFunctionWithDifferentHttpMethodsAndDifferentPathsController {

    @RequestMapping("/{id}", method = [PUT])
    fun todo() { }

    @RequestMapping
    fun todos() { }
}

@Controller
@RequestMapping("/todos", method = [PUT, PATCH, TRACE])
open class RequestMappingDefinedOnClassWithMultipleMethodsController {

    @RequestMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos", method = [PUT, PATCH, TRACE])
open class RequestMappingDefinedOnFunctionWithMultipleMethodsController {

    @RequestMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos", method = [POST])
open class RequestMappingWithPostAndGetMappingInCombinationController {

    @GetMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos", method = [POST])
open class RequestMappingWithPostAndDeleteMappingInCombinationController {

    @DeleteMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos", method = [POST])
open class RequestMappingWithPostAndPatchMappingInCombinationController {

    @PatchMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos", method = [TRACE])
open class RequestMappingWithTraceAndPostMappingInCombinationController {

    @PostMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos", method = [POST])
open class RequestMappingWithPostAndPutMappingInCombinationController {

    @PutMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos")
open class EmptyRequestMappingAndGetMappingInCombinationController {

    @GetMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos")
open class EmptyRequestMappingAndDeleteMappingInCombinationController {

    @DeleteMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos")
open class EmptyRequestMappingAndPatchMappingInCombinationController {

    @PatchMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos")
open class EmptyRequestMappingAndPostMappingInCombinationController {

    @PostMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos")
open class EmptyRequestMappingAndPutMappingInCombinationController {

    @PutMapping
    fun todo() { }
}

@Controller
open class RequestMappingFirstAndGetMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @GetMapping
    fun todo() { }
}

@Controller
open class GetMappingFirstAndRequestMappingBothOnFunctionController {

    @GetMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@Controller
open class RequestMappingFirstAndDeleteMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @DeleteMapping
    fun todo() { }
}

@Controller
open class DeleteMappingFirstAndRequestMappingBothOnFunctionController {

    @DeleteMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@Controller
open class RequestMappingFirstAndPatchMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @PatchMapping
    fun todo() { }
}

@Controller
open class PatchMappingFirstAndRequestMappingBothOnFunctionController {

    @PatchMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@Controller
open class RequestMappingFirstAndPostMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @PostMapping
    fun todo() { }
}

@Controller
open class PostMappingFirstAndRequestMappingBothOnFunctionController {

    @PostMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@Controller
open class RequestMappingFirstAndPutMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @PutMapping
    fun todo() { }
}

@Controller
open class PutMappingFirstAndRequestMappingBothOnFunctionController {

    @PutMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@Controller
open class GetMappingController {

    @GetMapping("/todos")
    fun todos() { }
}

@Controller
open class DeleteMappingController {

    @DeleteMapping("/todos")
    fun todos() { }
}

@Controller
open class PatchMappingController {

    @PatchMapping("/todos")
    fun todos() { }
}

@Controller
open class PostMappingController {

    @PostMapping("/todos")
    fun todos() { }
}

@Controller
open class PutMappingController {

    @PutMapping("/todos")
    fun todos() { }
}

@Controller
open class MultipleHttpMethodMappingAnnotationsController {

    @GetMapping("/todos")
    @PostMapping("/todos")
    @DeleteMapping("/todos")
    fun tdos() { }
}

@Controller
open class HttpMethodsForDefaultErrorEndpointController {

    @GetMapping("/todos")
    fun todos() { }
}