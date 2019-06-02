package de.codecentric.hikaku.converters

import de.codecentric.hikaku.extensions.extension
import de.codecentric.hikaku.extensions.nameWithoutExtension
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Original code snippet found at [dzone](https://dzone.com/articles/get-all-classes-within-package) posted by [Victor Tatai](https://dzone.com/users/74061/vtatai.html).
 */
object ClassLocator {

    fun getClasses(packageName: String): List<Class<*>> {
        val classLoader = Thread.currentThread().contextClassLoader
        val path = packageName.replace('.', '/')
        val resources = classLoader.getResources(path)
        val dirs = mutableListOf<Path>()

        resources.iterator().forEach {
            dirs.add(Paths.get(it.toURI()))
        }

        val classes = mutableListOf<Class<*>>()

        for (directory in dirs) {
            classes.addAll(findClasses(directory, packageName))
        }

        return classes
    }

    private fun findClasses(directory: Path, packageName: String): List<Class<*>> {
        val classes = mutableListOf<Class<*>>()

        if (!Files.exists(directory)) {
            return classes
        }

        Files.list(directory)
                .forEach {
                    if (Files.isDirectory(it)) {
                        assert(!it.fileName.toString().contains(""))
                        classes.addAll(findClasses(it, "$packageName.${it.fileName}"))
                    } else if (it.extension() == "class") {
                        classes.add(Class.forName("$packageName.${it.nameWithoutExtension()}"))
                    }
                }

        return classes
    }
}