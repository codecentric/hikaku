package de.codecentric.hikaku.reporter

import de.codecentric.hikaku.SupportedFeatures
import de.codecentric.hikaku.SupportedFeatures.*
import de.codecentric.hikaku.endpoints.Endpoint


private const val SEPARATOR = ", "

/**
 * Simply prints the result to stdout.
 */
class CommandLineReporter: Reporter {

    override fun report(endpointMatchResults: MatchResult) {
        val heading = "Hikaku test result:"

        println("\n")
        println(heading)
        println("#".repeat(heading.length))

        if (endpointMatchResults.notFound.isEmpty() && endpointMatchResults.notExpected.isEmpty()) {
            println ("")
            println ("✅ Test successful. Specification and implementation match.")
        }


        if (endpointMatchResults.notFound.isNotEmpty()) {
            println("\n👀 Expected, but unable to find:")

            endpointMatchResults.notFound.forEach {
                printEndpoint(endpointMatchResults.supportedFeatures, it)
            }
        }

        if (endpointMatchResults.notExpected.isNotEmpty()) {
            println("\n👻 Unexpected, but found:")

            endpointMatchResults.notExpected.forEach {
                printEndpoint(endpointMatchResults.supportedFeatures, it)
            }
        }
    }

    private fun printEndpoint(supportedFeatures: SupportedFeatures, endpoint: Endpoint) {
        var path = "< ${endpoint.httpMethod} ${endpoint.path}"

        supportedFeatures.forEach {
            path += when(it) {
                Feature.QueryParameter -> " ${endpoint.queryParameters.joinToString(separator = SEPARATOR)}"
                Feature.PathParameter -> " ${endpoint.pathParameters.joinToString(separator = SEPARATOR)}"
                Feature.HeaderParameter -> " ${endpoint.headerParameters.joinToString(separator = SEPARATOR)}"
                Feature.Produces -> " ${endpoint.produces.joinToString(separator = SEPARATOR)}"
                Feature.Consumes -> " ${endpoint.consumes.joinToString(separator = SEPARATOR)}"
            }
        }

        println("$path>")
    }
}