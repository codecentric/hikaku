package de.codecentric.hikaku.converter.spring.extensions

import de.codecentric.hikaku.endpoints.HttpMethod
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition
import org.springframework.web.servlet.mvc.method.RequestMappingInfo

fun RequestMethodsRequestCondition.httpMethods(): Set<HttpMethod> {
    return this.methods
            .map {
                when (it) {
                    RequestMethod.GET -> HttpMethod.GET
                    RequestMethod.POST -> HttpMethod.POST
                    RequestMethod.HEAD -> HttpMethod.HEAD
                    RequestMethod.PUT -> HttpMethod.PUT
                    RequestMethod.PATCH -> HttpMethod.PATCH
                    RequestMethod.DELETE -> HttpMethod.DELETE
                    RequestMethod.TRACE -> HttpMethod.TRACE
                    RequestMethod.OPTIONS -> HttpMethod.OPTIONS
                    null -> HttpMethod.OPTIONS
                }
            }
            .toSet()
}

fun RequestMappingInfo.httpMethods() = this.methodsCondition.httpMethods()

fun RequestMappingInfo.paths(): Set<String> {
    return this.patternsCondition.patterns
}