package de.codecentric.hikaku.reporter

import de.codecentric.hikaku.HikakuConfig
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.matcher.MatchResult
import de.codecentric.hikaku.matcher.MatchResultGroup

/**
 * A [Reporter] will receive the [List] of [MatchResultGroup]s before [assert] is called with the end result.
 * Hikaku provides two implementations. The [CommandLineReporter] which prints the results to stdout.
 * It is the default in [HikakuConfig]. The [NoOperationReporter] omits the results and does nothing.
 * @param endpointMatchResults [List] of all [MatchResult]s for each [Endpoint] grouped together as a [MatchResultGroup].
 */
interface Reporter {
    fun report(endpointMatchResults: List<MatchResultGroup>)
}