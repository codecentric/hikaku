package de.codecentric.hikaku.converters.wadl

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converters.AbstractEndpointConverter
import de.codecentric.hikaku.converters.EndpointConverterException
import de.codecentric.hikaku.converters.wadl.extensions.getAttribute
import de.codecentric.hikaku.endpoints.*
import de.codecentric.hikaku.extensions.checkFileValidity
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants.NODESET
import javax.xml.xpath.XPathFactory

/**
 * Extracts and converts [Endpoint]s from a *.wadl* file.
 */
class WadlConverter private constructor(private val wadl: String) : AbstractEndpointConverter() {

    @JvmOverloads
    constructor(wadlFile: File, charset: Charset = UTF_8): this(wadlFile.toPath(), charset)
    @JvmOverloads
    constructor(wadlFile: Path, charset: Charset = UTF_8): this(readFileContent(wadlFile, charset))

    override val supportedFeatures = SupportedFeatures(
            Feature.QueryParameters,
            Feature.HeaderParameters,
            Feature.PathParameters,
            Feature.MatrixParameters,
            Feature.Produces,
            Feature.Consumes
    )

    private val xPath = XPathFactory
            .newInstance()
            .newXPath()

    override fun convert(): Set<Endpoint> {
        try {
            return parseWadl()
        } catch (throwable: Throwable) {
            throw EndpointConverterException(throwable)
        }
    }

    private fun parseWadl(): Set<Endpoint> {
        val doc = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(InputSource(StringReader(wadl)))

        val resources = xPath.evaluate("//resource", doc, NODESET) as NodeList

        val endpoints = mutableSetOf<Endpoint>()

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
                            matrixParameters = extractMatrixParameters(method),
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

    private fun extractMatrixParameters(method: Node): Set<MatrixParameter> {
        return extractParameter(method, "matrix")
                .entries
                .map { MatrixParameter(it.key, it.value) }
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
}

private fun readFileContent(wadlFile: Path, charset: Charset): String {
    val fileContentBuilder = StringBuilder()

    try {
        wadlFile.checkFileValidity(".wadl")

        Files.readAllLines(wadlFile, charset)
                .map { line ->
                    fileContentBuilder
                            .append(line)
                            .append("\n")
                }
    } catch (throwable: Throwable) {
        throw EndpointConverterException(throwable)
    }

    val fileContent = fileContentBuilder.toString()

    if (fileContent.isBlank()) {
        throw EndpointConverterException("Given WADL is blank.")
    }

    return fileContent
}