package de.codecentric.hikaku.converters.spring.deprecation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
open class DummyApp

data class Todo(val description: String = "")

@RestController
open class NoDeprecationController {

    @GetMapping("/todos")
    fun todos() = Todo()
}

@RestController
@Deprecated("Test")
open class DeprecatedClassController {

    @GetMapping("/todos")
    fun todos() = Todo()
}

@RestController
open class DeprecatedFunctionController {

    @GetMapping("/todos")
    @Deprecated("Test")
    fun todos() = Todo()
}