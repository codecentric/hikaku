package de.codecentric.hikaku.converter.spring.httpmethod

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*

@SpringBootApplication
open class DummyApp

@RestController
@RequestMapping("/todos", method = [GET])
open class RequestMappingDefinedOnClassWithExplicitGetMethodController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionWithExplicitGetMethodController {

    @RequestMapping("/todos", method = [GET])
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [POST])
open class RequestMappingDefinedOnClassWithExplicitPostMethodController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionWithExplicitPostMethodController {

    @RequestMapping("/todos", method = [POST])
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [HEAD])
open class RequestMappingDefinedOnClassWithExplicitHeadMethodController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionWithExplicitHeadMethodController {

    @RequestMapping("/todos", method = [HEAD])
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [PUT])
open class RequestMappingDefinedOnClassWithExplicitPutMethodController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionWithExplicitPutMethodController {

    @RequestMapping("/todos", method = [PUT])
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [PATCH])
open class RequestMappingDefinedOnClassWithExplicitPatchMethodController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionWithExplicitPatchMethodController {

    @RequestMapping("/todos", method = [PATCH])
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [DELETE])
open class RequestMappingDefinedOnClassWithExplicitDeleteMethodController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionWithExplicitDeleteMethodController {

    @RequestMapping("/todos", method = [DELETE])
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [TRACE])
open class RequestMappingDefinedOnClassWithExplicitTraceMethodController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionWithExplicitTraceMethodController {

    @RequestMapping("/todos", method = [TRACE])
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [OPTIONS])
open class RequestMappingDefinedOnClassWithExplicitOptionsMethodController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionWithExplicitOptionsMethodController {

    @RequestMapping("/todos", method = [OPTIONS])
    fun todos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingDefinedOnClassNoHttpMethodDefinedController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingDefinedOnFunctionNoHttpMethodDefinedController {

    @RequestMapping("/todos")
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [PUT])
open class RequestMappingDefinedOnClassAndFunctionWithDifferentHttpMethodsController {

    @RequestMapping(method = [PATCH])
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [GET])
open class RequestMappingDefinedOnClassAndFunctionWithDifferentHttpMethodsAndDifferentPathsController {

    @RequestMapping("/{id}", method = [PUT])
    fun todo() { }

    @RequestMapping
    fun todos() { }
}

@RestController
@RequestMapping("/todos", method = [PUT, PATCH, TRACE])
open class RequestMappingDefinedOnClassWithMultipleMethodsController {

    @RequestMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos", method = [PUT, PATCH, TRACE])
open class RequestMappingDefinedOnFunctionWithMultipleMethodsController {

    @RequestMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos", method = [POST])
open class RequestMappingWithPostAndGetMappingInCombinationController {

    @GetMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos", method = [POST])
open class RequestMappingWithPostAndDeleteMappingInCombinationController {

    @DeleteMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos", method = [POST])
open class RequestMappingWithPostAndPatchMappingInCombinationController {

    @PatchMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos", method = [TRACE])
open class RequestMappingWithTraceAndPostMappingInCombinationController {

    @PostMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos", method = [POST])
open class RequestMappingWithPostAndPutMappingInCombinationController {

    @PutMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos")
open class EmptyRequestMappingAndGetMappingInCombinationController {

    @GetMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos")
open class EmptyRequestMappingAndDeleteMappingInCombinationController {

    @DeleteMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos")
open class EmptyRequestMappingAndPatchMappingInCombinationController {

    @PatchMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos")
open class EmptyRequestMappingAndPostMappingInCombinationController {

    @PostMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos")
open class EmptyRequestMappingAndPutMappingInCombinationController {

    @PutMapping
    fun todo() { }
}

@RestController
open class RequestMappingFirstAndGetMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @GetMapping
    fun todo() { }
}

@RestController
open class GetMappingFirstAndRequestMappingBothOnFunctionController {

    @GetMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@RestController
open class RequestMappingFirstAndDeleteMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @DeleteMapping
    fun todo() { }
}

@RestController
open class DeleteMappingFirstAndRequestMappingBothOnFunctionController {

    @DeleteMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@RestController
open class RequestMappingFirstAndPatchMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @PatchMapping
    fun todo() { }
}

@RestController
open class PatchMappingFirstAndRequestMappingBothOnFunctionController {

    @PatchMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@RestController
open class RequestMappingFirstAndPostMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @PostMapping
    fun todo() { }
}

@RestController
open class PostMappingFirstAndRequestMappingBothOnFunctionController {

    @PostMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@RestController
open class RequestMappingFirstAndPutMappingBothOnFunctionController {

    @RequestMapping("/todos")
    @PutMapping
    fun todo() { }
}

@RestController
open class PutMappingFirstAndRequestMappingBothOnFunctionController {

    @PutMapping
    @RequestMapping("/todos")
    fun todo() { }
}

@RestController
open class GetMappingController {

    @GetMapping("/todos")
    fun todos() { }
}

@RestController
open class DeleteMappingController {

    @DeleteMapping("/todos")
    fun todos() { }
}

@RestController
open class PatchMappingController {

    @PatchMapping("/todos")
    fun todos() { }
}

@RestController
open class PostMappingController {

    @PostMapping("/todos")
    fun todos() { }
}

@RestController
open class PutMappingController {

    @PutMapping("/todos")
    fun todos() { }
}

@RestController
open class MultipleHttpMethodMappingAnnotationsController {

    @GetMapping("/todos")
    @PostMapping("/todos")
    @DeleteMapping("/todos")
    fun tdos() { }
}