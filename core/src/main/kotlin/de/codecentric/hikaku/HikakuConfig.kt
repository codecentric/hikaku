package de.codecentric.hikaku

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.HEAD
import de.codecentric.hikaku.endpoints.HttpMethod.OPTIONS
import de.codecentric.hikaku.reporters.CommandLineReporter
import de.codecentric.hikaku.reporters.MatchResult
import de.codecentric.hikaku.reporters.Reporter

/**
 * Configuration for [Hikaku] class. It lets you partially control the matching process.
 * @param reporter The [MatchResult] will be passed to one or many [Reporter] before the test either fails or succeeds. Default is a [CommandLineReporter] that prints the results to [System.out].
 * @param filter Filtering rule: [Endpoint]s matching the predicate will be ignored.
 */
data class HikakuConfig
@JvmOverloads constructor(
        val reporter: List<Reporter> = listOf(CommandLineReporter()),
        val filter: List<(Endpoint) -> Boolean> = emptyList()
)