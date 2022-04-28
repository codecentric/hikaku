package de.codecentric.hikaku.converters.spring.extensions

import org.springframework.web.servlet.mvc.method.RequestMappingInfo

internal fun RequestMappingInfo.paths(): Set<String> = buildSet {
  patternsCondition?.patterns?.let(::addAll)
  pathPatternsCondition?.patterns?.map { it.patternString }?.let(::addAll)
}
