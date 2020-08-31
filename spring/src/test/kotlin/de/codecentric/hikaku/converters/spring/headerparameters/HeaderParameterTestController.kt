package de.codecentric.hikaku.converters.spring.headerparameters

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@SpringBootApplication
open class DummyApp

@Controller
@Suppress("UNUSED_PARAMETER")
open class HeaderParameterNamedByVariableController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader allowCache: Boolean) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class HeaderParameterNamedByValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(value = "allow-cache") variable: Boolean) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class HeaderParameterNamedByNameAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(name = "allow-cache") variable: Boolean) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class HeaderParameterHavingBothNameAndValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(value="valueAttribute", name = "nameAttribute") variable: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class HeaderParameterOptionalController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(name = "allow-cache", required = false) variable: Boolean) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class HeaderParameterOptionalBecauseOfDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(name = "tracker-id", defaultValue = "unknown") variable: Boolean) { }
}


@Controller
@Suppress("UNUSED_PARAMETER")
open class HeaderParameterOnDefaultErrorEndpointController {

    @GetMapping("/todos")
    fun getAllTodos(@RequestHeader(value = "allow-cache") variable: Boolean) { }
}
