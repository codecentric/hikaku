package de.codecentric.hikaku.reporter

/**
 * A [Reporter] will receive the [MatchResult] before the test terminates.
 */
interface Reporter {
    fun report(endpointMatchResult: MatchResult)
}