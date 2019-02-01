package de.codecentric.hikaku.converter.spring.headerparameter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
open class DummyApp

@RestController
open class HeaderParameterNamedByVariableController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader useCache: Boolean) { }
}

@RestController
open class HeaderParameterNamedByValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(value = "use-cache") variable: Boolean) { }
}

@RestController
open class HeaderParameterNamedByNameAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(name = "use-cache") variable: Boolean) { }
}

@RestController
open class HeaderParameterHavingBothNameAndValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(value="valueAttribute", name = "nameAttribute") variable: String) { }
}

@RestController
open class HeaderParameterOptionalController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(name = "use-cache", required = false) variable: Boolean) { }
}

@RestController
open class HeaderParameterOptionalBecauseOfDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(name = "tracker-id", defaultValue = "unknown") variable: Boolean) { }
}