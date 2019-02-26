package de.codecentric.hikaku.converters.wadl.extensions

import org.w3c.dom.Node

internal fun Node.getAttribute(identifier: String): String {
    return this.attributes.getNamedItem(identifier).textContent
}