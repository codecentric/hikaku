package de.codecentric.hikaku.converters.spring.path

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET

@SpringBootApplication
open class DummyApp

@Controller
@RequestMapping("/todos", "/todo/list", method = [GET])
open class RequestMappingOnClassWithMultiplePathsController {

    @RequestMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos", method = [GET])
open class RequestMappingIgnoreErrorPathController {

    @RequestMapping
    fun todo() { }
}

@Controller
@RequestMapping("/todos/{id:[0-9]+}", method = [GET])
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOnClassProvidingRegexForPathVariableController {

    @RequestMapping
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@Controller
open class RequestMappingOnFunctionWithMultiplePathsController {

    @RequestMapping("/todos", "/todo/list", method = [GET])
    fun todo() { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOnFunctionProvidingRegexForPathVariableController {

    @RequestMapping("/todos/{id:[0-9]+}", method = [GET])
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@Controller
open class GetMappingOnFunctionWithMultiplePathsController {

    @GetMapping("/todos", "/todo/list")
    fun todo() { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class GetMappingProvidingRegexForPathVariableController {

    @GetMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@Controller
open class DeleteMappingOnFunctionWithMultiplePathsController {

    @DeleteMapping("/todos", "/todo/list")
    fun todo() { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class DeleteMappingProvidingRegexForPathVariableController {

    @DeleteMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@Controller
open class PatchMappingOnFunctionWithMultiplePathsController {

    @PatchMapping("/todos", "/todo/list")
    fun todo() { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PatchMappingProvidingRegexForPathVariableController {

    @PatchMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@Controller
open class PostMappingOnFunctionWithMultiplePathsController {

    @PostMapping("/todos", "/todo/list")
    fun todo() { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PostMappingProvidingRegexForPathVariableController {

    @PostMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@Controller
open class PutMappingOnFunctionWithMultiplePathsController {

    @PutMapping("/todos", "/todo/list")
    fun todo() { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PutMappingProvidingRegexForPathVariableController {

    @PutMapping("/todos/{id:[0-9]+}")
    fun todo(@PathVariable(name = "id") variable: Int) { }
}

@Controller
@RequestMapping("/todo", method = [GET])
open class RequestMappingNestedPathController {

    @RequestMapping("/list")
    fun todo() { }
}

@Controller
@RequestMapping("/todo")
open class GetMappingNestedPathController {

    @GetMapping("/list")
    fun todo() { }
}

@Controller
@RequestMapping("/todo")
open class DeleteMappingNestedPathController {

    @DeleteMapping("/list")
    fun todo() { }
}

@Controller
@RequestMapping("/todo")
open class PatchMappingNestedPathController {

    @PatchMapping("/list")
    fun todo() { }
}

@Controller
@RequestMapping("/todo")
open class PostMappingNestedPathController {

    @PostMapping("/list")
    fun todo() { }
}

@Controller
@RequestMapping("/todo")
open class PutMappingNestedPathController {

    @PutMapping("/list")
    fun todo() { }
}
