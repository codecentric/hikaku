package de.codecentric.hikaku.converter.spring.extensions

import org.springframework.web.servlet.mvc.method.RequestMappingInfo

fun RequestMappingInfo.produces(): Set<String> {
    return this.producesCondition
            .expressions
            .map { it.mediaType.toString() }
            .toSet()
}