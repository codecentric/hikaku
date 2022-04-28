package de.codecentric.hikaku.converters.spring.extensions

import org.springframework.web.servlet.mvc.method.RequestMappingInfo

internal fun RequestMappingInfo.paths(): Set<String> =
  patternsCondition?.patterns
    ?: pathPatternsCondition?.patterns?.map { it.patternString }?.toSet()
    ?: emptySet()
