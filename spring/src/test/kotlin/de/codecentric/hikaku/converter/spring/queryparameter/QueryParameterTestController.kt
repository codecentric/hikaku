package de.codecentric.hikaku.converter.spring.queryparameter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
open class DummyApp

@RestController
open class QueryParameterNamedByVariableController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam tag: String) { }
}

@RestController
open class QueryParameterNamedByValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(value = "tag") variable: String) { }
}

@RestController
open class QueryParameterNamedByNameAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(name = "tag") variable: String) { }
}

@RestController
open class QueryParameterHavingBothNameAndValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(value="valueAttribute", name = "nameAttribute") variable: String) { }
}

@RestController
open class QueryParameterOptionalController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(name = "tag", required = false) variable: String) { }
}

@RestController
open class QueryParameterOptionalBecauseOfDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(name = "tag", defaultValue = "mytag") variable: String) { }
}

@RestController
open class QueryParameterOnDefaultErrorEndpointController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(value = "tag", required = false) variable: String) { }
}