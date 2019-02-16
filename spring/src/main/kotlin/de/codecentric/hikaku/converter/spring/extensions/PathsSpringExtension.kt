package de.codecentric.hikaku.converter.spring.extensions

import org.springframework.web.servlet.mvc.method.RequestMappingInfo

internal fun RequestMappingInfo.paths(): Set<String> {
    return this.patternsCondition.patterns
}