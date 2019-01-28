package de.codecentric.hikaku.converter.wadl.extensions

import org.w3c.dom.Node

fun Node.getAttribute(identifier: String): String {
    return this.attributes.getNamedItem(identifier).textContent
}