package de.codecentric.hikaku.extensions

import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

fun KClass<*>.isUnit(): Boolean {
    return this.isInstance(Unit) ||
    this.jvmName == "java.lang.Void" ||
    this.jvmName == "void"
}

fun KClass<*>.isString(): Boolean {
    return this.isInstance(String) ||
            this.jvmName == "java.lang.String"
}
