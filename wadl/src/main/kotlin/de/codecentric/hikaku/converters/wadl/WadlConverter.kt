package de.codecentric.hikaku.converters.wadl

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.wadl.extensions.getAttribute
import de.codecentric.hikaku.endpoints.*
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants.NODESET
import javax.xml.xpath.XPathFactory

/**
 * Extracts and converts [Endpoint]s from a *.wadl* file.
 *
 * In Java invoke via: `WadlConverter.usingPath(Paths.get("specification.wadl");`
 * or `WadlConverter.usingFile(new File("specification.wadl");`
 */
class WadlConverter private constructor(private val wadl: String) : AbstractEndpointConverter() {

    init {
        if (wadl.isBlank()) {
            throw IllegalArgumentException("Given WADL is blank.")
        }
    }

    override val supportedFeatures = SupportedFeatures(
            Feature.QueryParameter,
            Feature.HeaderParameter,
            Feature.PathParameter,
            Feature.Produces,
            Feature.Consumes
    )

    private val xPath = XPathFactory
            .newInstance()
            .newXPath()

    override fun convert(): Set<Endpoint> {
        val doc = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(InputSource(StringReader(wadl)))

        val resources = xPath.evaluate("//resource", doc, NODESET) as NodeList

        val endpoints: MutableSet<Endpoint> = mutableSetOf()

        for (index in 0 until resources.length) {
            endpoints.addAll(createEndpoints(resources.item(index)))
        }

        return endpoints
    }

    private fun createEndpoints(resourceElement: Node): Set<Endpoint> {
        val path = resourceElement.getAttribute("path")

        val methods = xPath.evaluate("//resource[@path=\"$path\"]//method", resourceElement.childNodes, NODESET) as NodeList
        val endpoints: MutableSet<Endpoint> = mutableSetOf()

        for (i in 0 until methods.length) {
            val method = methods.item(i)
            val httpMethod = HttpMethod.valueOf(method.getAttribute("name"))

            endpoints.add(
                    Endpoint(
                            path = path,
                            httpMethod = httpMethod,
                            queryParameters = extractQueryParameters(method),
                            headerParameters = extractHeaderParameters(method),
                            pathParameters = extractPathParameters(method),
                            produces = extractResponseMediaTypes(method),
                            consumes = extractConsumesMediaTypes(method)
                    )
            )
        }

        return endpoints
    }

    private fun extractResponseMediaTypes(method: Node) = extractMediaTypes(method, "response")

    private fun extractConsumesMediaTypes(method: Node) = extractMediaTypes(method, "request")

    private fun extractMediaTypes(method: Node, xmlBaseElement: String): Set<String> {
        val representations = xPath.evaluate("//$xmlBaseElement/representation", method.childNodes, NODESET) as NodeList
        val mediaTypes: MutableSet<String> = mutableSetOf()

        for (i in 0 until representations.length) {
            val parameter = representations.item(i)
            mediaTypes += parameter.getAttribute("mediaType")
        }

        return mediaTypes
    }

    private fun extractPathParameters(method: Node): Set<PathParameter> {
        return extractParameter(method, "template")
                .entries
                .map { PathParameter(it.key) }
                .toSet()
    }

    private fun extractQueryParameters(method: Node): Set<QueryParameter> {
        return extractParameter(method, "query")
                .entries
                .map { QueryParameter(it.key, it.value) }
                .toSet()
    }

    private fun extractHeaderParameters(method: Node): Set<HeaderParameter> {
        return extractParameter(method, "header")
                .entries
                .map { HeaderParameter(it.key, it.value) }
                .toSet()
    }

    private fun extractParameter(method: Node, style: String): Map<String, Boolean> {
        val parameters = xPath.evaluate("//param[@style=\"$style\"]", method.childNodes, NODESET) as NodeList
        val parameterMap: MutableMap<String, Boolean> = mutableMapOf()

        for (i in 0 until parameters.length) {
            val parameter = parameters.item(i)
            val parameterName = parameter.getAttribute("name")
            val isParameterRequired = "true" == parameter.getAttribute("required")

            parameterMap[parameterName] = isParameterRequired
        }

        return parameterMap
    }

    companion object {
        @JvmStatic
        @JvmName("usingPath")
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

        @JvmStatic
        @JvmName("usingFile")
        operator fun invoke(wadlFile: File): WadlConverter {
            return WadlConverter(wadlFile.toPath())
        }

        private fun isValidFileExtension(wadlFile: Path) = wadlFile.fileName.toString().endsWith(".wadl")
    }
}