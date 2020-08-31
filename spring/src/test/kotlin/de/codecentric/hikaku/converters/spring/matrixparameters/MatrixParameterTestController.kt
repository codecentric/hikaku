package de.codecentric.hikaku.converters.spring.matrixparameters

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.MatrixVariable

@SpringBootApplication
open class DummyApp

@Controller
@Suppress("UNUSED_PARAMETER")
open class MatrixParameterNamedByVariableController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable tag: Boolean) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class MatrixParameterNamedByValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(value = "tag") variable: Boolean) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class MatrixParameterNamedByNameAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(name = "tag") variable: Boolean) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class MatrixParameterHavingBothNameAndValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(value="valueAttribute", name = "nameAttribute") variable: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class MatrixParameterOptionalController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(name = "tag", required = false) variable: Boolean) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class MatrixParameterOptionalBecauseOfDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(name = "tag", defaultValue = "unknown") variable: Boolean) { }
}


@Controller
@Suppress("UNUSED_PARAMETER")
open class MatrixParameterOnDefaultErrorEndpointController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(value = "tag") variable: Boolean) { }
}
