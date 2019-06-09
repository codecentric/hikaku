package de.codecentric.hikaku.reporters

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.*
import de.codecentric.hikaku.endpoints.*

/**
 * Simply prints the result to [System.out].
 */
class CommandLineReporter : Reporter {

    override fun report(endpointMatchResult: MatchResult) {
        val heading = "hikaku test result:"

        println("\n")
        println(heading)
        println("#".repeat(heading.length))

        val features = endpointMatchResult.supportedFeatures.joinToString(separator = ", ")
        println("The following features were used for matching: HttpMethod, Path, $features")


        if (endpointMatchResult.notFound.isEmpty() && endpointMatchResult.notExpected.isEmpty()) {
            println ("")
            println ("✅ Test successful. Specification and implementation match.")
        }


        if (endpointMatchResult.notFound.isNotEmpty()) {
            println("\n👀 Expected, but unable to find:")

            endpointMatchResult.notFound.forEach {
                printEndpoint(endpointMatchResult.supportedFeatures, it)
            }
        }

        if (endpointMatchResult.notExpected.isNotEmpty()) {
            println("\n👻 Unexpected, but found:")

            endpointMatchResult.notExpected.forEach {
                printEndpoint(endpointMatchResult.supportedFeatures, it)
            }
        }
    }

    private fun printEndpoint(supportedFeatures: SupportedFeatures, endpoint: Endpoint) {
        var path = "< ${endpoint.httpMethod} ${endpoint.path}"

        supportedFeatures.forEach {
            path += when(it) {
                Feature.QueryParameters -> listQueryParameters(endpoint.queryParameters)
                Feature.PathParameters -> listPathParameters(endpoint.pathParameters)
                Feature.HeaderParameters -> listHeaderParameter(endpoint.headerParameters)
                Feature.MatrixParameters -> listMatrixParameter(endpoint.matrixParameters)
                Feature.Consumes -> listRequestMediaTypes(endpoint.consumes)
                Feature.Produces -> listResponseMediaTypes(endpoint.produces)
                Feature.Deprecation -> if (endpoint.deprecated) "  Deprecated" else ""
            }
        }

        println("$path >")
    }

    private fun listQueryParameters(queryParameters: Set<QueryParameter>) =
            "  QueryParameters[${queryParameters.joinToString {
                "${it.parameterName} (${if(it.required) "required" else "optional"})"
            }}]"

    private fun listPathParameters(pathParameters: Set<PathParameter>) =
            "  PathParameters[${pathParameters.joinToString {
                it.parameterName
            }}]"

    private fun listHeaderParameter(headerParameters: Set<HeaderParameter>) =
            "  HeaderParameters[${headerParameters.joinToString {
                "${it.parameterName} (${if(it.required) "required" else "optional"})"
            }}]"

    private fun listMatrixParameter(matrixParameters: Set<MatrixParameter>) =
            "  MatrixParameters[${matrixParameters.joinToString {
                "${it.parameterName} (${if(it.required) "required" else "optional"})"
            }}]"

    private fun listRequestMediaTypes(requestMediaTypes: Set<String>) =
            "  Consumes[${requestMediaTypes.joinToString()}]"

    private fun listResponseMediaTypes(responseMediaTypes: Set<String>) =
            "  Produces[${responseMediaTypes.joinToString()}]"
}