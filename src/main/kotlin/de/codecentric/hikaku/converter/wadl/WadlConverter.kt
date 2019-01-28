package de.codecentric.hikaku.converter.wadl

import de.codecentric.hikaku.converter.AbstractEndpointConverter
import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.converter.wadl.extensions.getAttribute
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants.*
import javax.xml.xpath.XPathFactory

class WadlConverter(private val wadl: String) : AbstractEndpointConverter() {

    init {
        if (wadl.isBlank()) {
            throw IllegalArgumentException("Given WADL is blank.")
        }
    }

    override val supportedFeatures = SupportedFeatures()

    override fun convert(): Set<Endpoint> {
        val doc = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(InputSource(StringReader(wadl)))

        val xPath = XPathFactory
                .newInstance()
                .newXPath()

        val resources = xPath.evaluate("//resource", doc, NODESET) as NodeList

        val endpoints: MutableSet<Endpoint> = mutableSetOf()

        for (index in 0 until resources.length) {
            endpoints.addAll(createEndpoints(resources.item(index)))
        }

        return endpoints
    }

    private fun createEndpoints(resourceElement: Node): Set<Endpoint> {
        val path = resourceElement.getAttribute("path")
        val xPath = XPathFactory
                .newInstance()
                .newXPath()

        val methods = xPath.evaluate("//resource[@path=\"$path\"]//method", resourceElement.childNodes, NODESET) as NodeList
        val endpoints: MutableSet<Endpoint> = mutableSetOf()

        for (i in 0 until methods.length) {
            val method = methods.item(i)
            val httpMethod = HttpMethod.valueOf(method.getAttribute("name"))

            endpoints.add(
                    Endpoint(
                            path = normalizePath(path),
                            httpMethod = httpMethod
                    )
            )
        }

        return endpoints
    }

    private fun normalizePath(path: String): String {
        return if (!path.startsWith("/")) {
            return "/$path"
        } else {
            path
        }
    }

    companion object {
        operator fun invoke(wadlFile: Path): WadlConverter {
            checkIfFileIsValid(wadlFile)

            val expectedFileContentBuilder = StringBuilder()

            Files.readAllLines(wadlFile, UTF_8)
                    .map { line ->
                        expectedFileContentBuilder
                                .append(line)
                                .append("\n")
                    }

            return WadlConverter(expectedFileContentBuilder.toString())
        }

        private fun checkIfFileIsValid(wadlFile: Path) {
            if (!Files.exists(wadlFile)) {
                throw IllegalArgumentException("Given WADL file does not exist.")
            }

            if (!Files.isRegularFile(wadlFile)) {
                throw IllegalArgumentException("Given WADL file is not a regular file.")
            }

            if (!isValidFileExtension(wadlFile)) {
                throw IllegalArgumentException("Given WADL file is not of type *.wadl.")
            }
        }

        operator fun invoke(wadlFile: File): WadlConverter {
            return WadlConverter(wadlFile.toPath())
        }

        private fun isValidFileExtension(wadlFile: Path) = wadlFile.fileName.toString().endsWith(".wadl")
    }
}