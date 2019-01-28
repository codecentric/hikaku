package de.codecentric.hikaku.reporter

import de.codecentric.hikaku.matcher.MatchResultGroup

interface Reporter {
    fun report(endpointMatchResults: List<MatchResultGroup>)
}