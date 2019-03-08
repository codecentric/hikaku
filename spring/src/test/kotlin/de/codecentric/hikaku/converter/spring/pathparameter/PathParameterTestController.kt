package de.codecentric.hikaku.converter.spring.pathparameter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.OPTIONS
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
open class DummyApp

@Controller
@RequestMapping("/todos")
open class PathParameterNamedByVariableController {

    @GetMapping("/{id}")
    fun getSpecificTodoItem(@PathVariable id: Int) { }
}

@Controller
@RequestMapping("/todos")
open class PathParameterNamedByValueAttributeController {

    @GetMapping("/{id}")
    fun getSpecificTodoItem(@PathVariable(value = "id") variable: Int) { }
}

@Controller
@RequestMapping("/todos")
open class PathParameterNamedByNameAttributeController {

    @GetMapping("/{id}")
    fun getSpecificTodoItem(@PathVariable(name = "id") variable: Int) { }
}

@Controller
@RequestMapping("/todos")
open class PathParameterHavingBothValueAndNameAttributeController {

    @GetMapping("/{id}")
    fun getSpecificTodoItem(@PathVariable(value = "valueAttribute", name = "nameAttribute") variable: Int) { }
}

@Controller
open class PathParameterSupportedForOptionsIfExplicitlyDefinedController {

    @RequestMapping("/todos/{id}", method = [OPTIONS])
    fun getSpecificTodoItem(@PathVariable id: Int) { }
}

@Controller
@RequestMapping("/todos")
open class PathParameterOnDefaultErrorEndpointController {

    @GetMapping("/{id}")
    fun getSpecificTodoItem(@PathVariable(value = "id") variable: Int) { }
}