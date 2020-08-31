package de.codecentric.hikaku.converters.spring.queryparameters

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@SpringBootApplication
open class DummyApp

@Controller
@Suppress("UNUSED_PARAMETER")
open class QueryParameterNamedByVariableController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam tag: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class QueryParameterNamedByValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(value = "tag") variable: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class QueryParameterNamedByNameAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(name = "tag") variable: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class QueryParameterHavingBothNameAndValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(value="valueAttribute", name = "nameAttribute") variable: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class QueryParameterOptionalController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(name = "tag", required = false) variable: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class QueryParameterOptionalBecauseOfDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(name = "tag", defaultValue = "mytag") variable: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class QueryParameterOnDefaultErrorEndpointController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestParam(value = "tag", required = false) variable: String) { }
}
