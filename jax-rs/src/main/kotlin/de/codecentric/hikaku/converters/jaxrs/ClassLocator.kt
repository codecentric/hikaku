package de.codecentric.hikaku.converters.jaxrs

import de.codecentric.hikaku.extensions.extension
import de.codecentric.hikaku.extensions.nameWithoutExtension
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

internal object ClassLocator {

    fun getClasses(packageName: String): List<Class<*>> {
        val classLoader = Thread.currentThread().contextClassLoader
        val path = packageName.replace('.', '/')
        val resources = classLoader.getResources(path)
        val dirs = mutableListOf<Path>()

        while (resources.hasMoreElements()) {
            val resource = resources.nextElement() as URL
            dirs.add(Paths.get(resource.file))
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
                        assert(!it.fileName.toString().contains("."))
                        classes.addAll(findClasses(it, "$packageName.${it.fileName}"))
                    } else if (it.extension() == "class") {
                        classes.add(Class.forName("$packageName.${it.nameWithoutExtension()}"))
                    }
                }

        return classes
    }
}