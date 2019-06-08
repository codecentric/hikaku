package de.codecentric.hikaku.converters.spring.deprecation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@SpringBootApplication
open class DummyApp

data class Todo(val description: String)

@Controller
open class NoDeprecationController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Deprecated("Test")
open class DeprecatedClassController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
open class DeprecatedFunctionController {

    @RequestMapping("/todos")
    @Deprecated("Test")
    fun todos(@RequestBody todo: Todo) { }
}