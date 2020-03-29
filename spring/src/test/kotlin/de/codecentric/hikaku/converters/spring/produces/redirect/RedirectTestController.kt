package de.codecentric.hikaku.converters.spring.produces.redirect

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@SpringBootApplication
open class DummyApp

@RestController
open class RedirectTestController {

    @GetMapping("/todos")
    fun todos(): RedirectView = RedirectView()
}
