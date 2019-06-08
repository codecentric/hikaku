package de.codecentric.hikaku.converters.spring.extensions

import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo

internal fun Map.Entry<RequestMappingInfo, HandlerMethod>.isEndpointDeprecated() =
        this.value.method.isAnnotationPresent(Deprecated::class.java)
                || this.value.method.declaringClass.isAnnotationPresent(Deprecated::class.java)