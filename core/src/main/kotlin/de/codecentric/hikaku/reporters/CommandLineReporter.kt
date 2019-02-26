package de.codecentric.hikaku.reporters

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.*
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.PathParameter
import de.codecentric.hikaku.endpoints.QueryParameter

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
        println("The following features were used for matching: $features")


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
                Feature.QueryParameter -> listQueryParameters(endpoint.queryParameters)
                Feature.PathParameter -> listPathParameters(endpoint.pathParameters)
                Feature.HeaderParameter -> listHeaderParameter(endpoint.headerParameters)
                Feature.Consumes -> listRequestMediaTypes(endpoint.consumes)
                Feature.Produces -> listResponseMediaTypes(endpoint.produces)
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

    private fun listRequestMediaTypes(requestMediaTypes: Set<String>) =
            "  Consumes[${requestMediaTypes.joinToString()}]"

    private fun listResponseMediaTypes(responseMediaTypes: Set<String>) =
            "  Produces[${responseMediaTypes.joinToString()}]"
}