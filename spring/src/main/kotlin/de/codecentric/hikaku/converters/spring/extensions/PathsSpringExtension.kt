package de.codecentric.hikaku.converters.spring.extensions

import org.springframework.web.servlet.mvc.method.RequestMappingInfo

internal fun RequestMappingInfo.paths(): Set<String> {
    if (this.patternsCondition == null) {
        return emptySet()
    }
    return this.patternsCondition?.patterns ?: emptySet()
}
