package de.codecentric.hikaku.extensions

import java.nio.file.Files
import java.nio.file.Path

fun Path.checkFileValidity(vararg extensions: String) {
    if (!Files.exists(this)) {
        throw IllegalArgumentException("Given file does not exist.")
    }

    if (!Files.isRegularFile(this)) {
        throw IllegalArgumentException("Given file is not a regular file.")
    }

    if (extensions.isNotEmpty()) {
        extensions.filter {
            this.fileName.toString().endsWith(it)
        }
        .ifEmpty {
            throw IllegalArgumentException("Given file is not of type ${extensions.joinToString()}")
        }
    }
}