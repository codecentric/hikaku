package de.codecentric.hikaku.reporter

/**
 * Receives the result and does nothing.
 */
class NoOperationReporter : Reporter {
    override fun report(endpointMatchResult: MatchResult) { }
}