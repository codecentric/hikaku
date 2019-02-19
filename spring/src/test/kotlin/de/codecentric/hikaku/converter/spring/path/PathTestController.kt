package de.codecentric.hikaku.converter.spring.path

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET

@SpringBootApplication
open class DummyApp

@RestController
@RequestMapping("/todos", "/todo/list", method = [GET])
open class RequestMappingOnClassWithMultiplePathsController {

    @RequestMapping
    fun todo() { }
}

@RestController
@RequestMapping("/todos/{id:[0-9]+}", method = [GET])
open class RequestMappingOnClassProvidingRegexForPathVariableController {

    @RequestMapping
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@RestController
open class RequestMappingOnFunctionWithMultiplePathsController {

    @RequestMapping("/todos", "/todo/list", method = [GET])
    fun todo() { }
}

@RestController
open class RequestMappingOnFunctionProvidingRegexForPathVariableController {

    @RequestMapping("/todos/{id:[0-9]+}", method = [GET])
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@RestController
open class GetMappingOnFunctionWithMultiplePathsController {

    @GetMapping("/todos", "/todo/list")
    fun todo() { }
}

@RestController
open class GetMappingProvidingRegexForPathVariableController {

    @GetMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@RestController
open class DeleteMappingOnFunctionWithMultiplePathsController {

    @DeleteMapping("/todos", "/todo/list")
    fun todo() { }
}

@RestController
open class DeleteMappingProvidingRegexForPathVariableController {

    @DeleteMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@RestController
open class PatchMappingOnFunctionWithMultiplePathsController {

    @PatchMapping("/todos", "/todo/list")
    fun todo() { }
}

@RestController
open class PatchMappingProvidingRegexForPathVariableController {

    @PatchMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@RestController
open class PostMappingOnFunctionWithMultiplePathsController {

    @PostMapping("/todos", "/todo/list")
    fun todo() { }
}

@RestController
open class PostMappingProvidingRegexForPathVariableController {

    @PostMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@RestController
open class PutMappingOnFunctionWithMultiplePathsController {

    @PutMapping("/todos", "/todo/list")
    fun todo() { }
}

@RestController
open class PutMappingProvidingRegexForPathVariableController {

    @PutMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@RestController
@RequestMapping("/todo", method = [GET])
open class RequestMappingNestedPathController {

    @RequestMapping("/list")
    fun todo() { }
}

@RestController
@RequestMapping("/todo")
open class GetMappingNestedPathController {

    @GetMapping("/list")
    fun todo() { }
}

@RestController
@RequestMapping("/todo")
open class DeleteMappingNestedPathController {

    @DeleteMapping("/list")
    fun todo() { }
}

@RestController
@RequestMapping("/todo")
open class PatchMappingNestedPathController {

    @PatchMapping("/list")
    fun todo() { }
}

@RestController
@RequestMapping("/todo")
open class PostMappingNestedPathController {

    @PostMapping("/list")
    fun todo() { }
}

@RestController
@RequestMapping("/todo")
open class PutMappingNestedPathController {

    @PutMapping("/list")
    fun todo() { }
}