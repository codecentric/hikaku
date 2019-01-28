package de.codecentric.hikaku.reporter

import de.codecentric.hikaku.matcher.EndpointMatchResult
import de.codecentric.hikaku.matcher.MatchResult
import de.codecentric.hikaku.matcher.MatchResultGroup
import de.codecentric.hikaku.matcher.PreCheckListSizeMatchResult

class CommandLineReporter: Reporter {

    override fun report(endpointMatchResults: List<MatchResultGroup>) {
        val heading = "Hikaku test result:"

        println("\n")
        println(heading)
        println("#".repeat(heading.length))

        endpointMatchResults.forEach {
            when(it) {
                is EndpointMatchResult -> printEndpointsMatchResult(it)
                is PreCheckListSizeMatchResult -> printPreCheckMatchResult(it)
            }
        }
    }

    private fun printEndpointsMatchResult(endpointMatchResult: EndpointMatchResult) {
        val httpMethodSpecification = endpointMatchResult.httpMethodMatchResult.specificationValue
        val pathSpecification = endpointMatchResult.pathMatchResult.specificationValue
        val headline = "$httpMethodSpecification $pathSpecification:"

        println("")
        println(headline)
        println("-".repeat(headline.length))
        println("")

        val httpMethodImplementation = endpointMatchResult.httpMethodMatchResult.implementationValue
        val httpMethodMatches = createMatchEmoji(endpointMatchResult.httpMethodMatchResult.matches)

        println("Expected http method [$httpMethodSpecification] and found [$httpMethodImplementation] $httpMethodMatches")

        val pathImplementation = endpointMatchResult.pathMatchResult.implementationValue
        val pathMatches = createMatchEmoji(endpointMatchResult.pathMatchResult.matches)

        println("Expected path [$pathSpecification] and found [$pathImplementation] $pathMatches")
        println("\n")

        printGenericMatchResults(endpointMatchResult.matchResults)
    }

    private fun printPreCheckMatchResult(preCheckListSizeMatchResult: PreCheckListSizeMatchResult) {
        printGenericMatchResults(preCheckListSizeMatchResult.matchResults)
    }

    private fun printGenericMatchResults(matchResults: List<MatchResult<*>>) {
        matchResults.forEach {
            println("Expected ${it.relatesTo} [${it.specificationValue}] and found [${it.implementationValue}] ${createMatchEmoji(it.matches)}")
        }
    }

    private fun createMatchEmoji(value: Boolean): String {
        return when (value) {
            true -> "✅"
            false -> "❌"
        }
    }
}