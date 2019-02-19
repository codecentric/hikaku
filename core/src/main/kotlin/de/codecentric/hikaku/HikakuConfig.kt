package de.codecentric.hikaku

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.HEAD
import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS
import de.codecentric.hikaku.reporter.MatchResult
import de.codecentric.hikaku.reporter.CommandLineReporter
import de.codecentric.hikaku.reporter.Reporter

/**
 * Configuration for [Hikaku] class. It lets you partially control the matching process.
 * @param ignorePaths If the path of an [Endpoint] is included in this [Set], all checks for that [Endpoint] will be skipped.
 * @param ignoreHttpMethodHead All checks for an [Endpoint] providing http method [HEAD] will be skipped if set to `true`.
 * @param ignoreHttpMethodOptions All checks for an [Endpoint] providing http method [OPTIONS] will be skipped if set to `true`.
 * @param reporter The [MatchResult] will be passed to one or many [Reporter] before the test either fails or succeeds. Default is a [CommandLineReporter] that prints the results to stdout.
 */
data class HikakuConfig(
        val ignorePaths: Set<String> = emptySet(),
        val ignoreHttpMethodHead: Boolean = false,
        val ignoreHttpMethodOptions: Boolean = false,
        val reporter: List<Reporter> = listOf(CommandLineReporter())
)