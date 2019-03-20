package de.codecentric.hikaku.converters.spring.matrixparameters

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.MatrixVariable

@SpringBootApplication
open class DummyApp

@Controller
open class MatrixParameterNamedByVariableController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable tag: Boolean) { }
}

@Controller
open class MatrixParameterNamedByValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(value = "tag") variable: Boolean) { }
}

@Controller
open class MatrixParameterNamedByNameAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(name = "tag") variable: Boolean) { }
}

@Controller
open class MatrixParameterHavingBothNameAndValueAttributeController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(value="valueAttribute", name = "nameAttribute") variable: String) { }
}

@Controller
open class MatrixParameterOptionalController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(name = "tag", required = false) variable: Boolean) { }
}

@Controller
open class MatrixParameterOptionalBecauseOfDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(name = "tag", defaultValue = "unknown") variable: Boolean) { }
}


@Controller
open class MatrixParameterOnDefaultErrorEndpointController {

    @GetMapping("/todos")
    fun getAllTodos(@MatrixVariable(value = "tag") variable: Boolean) { }
}