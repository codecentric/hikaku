package de.codecentric.hikaku.reporter

import de.codecentric.hikaku.matcher.MatchResultGroup

class NoOperationReporter : Reporter {
    override fun report(endpointMatchResults: List<MatchResultGroup>) { }
}