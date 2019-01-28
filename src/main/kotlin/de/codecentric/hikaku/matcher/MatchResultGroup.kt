package de.codecentric.hikaku.matcher

interface MatchResultGroup {
    val matchResults: List<MatchResult<*>>

    fun matches(): Boolean {
        return matchResults
                .map { it.matches }
                .fold(true) { lh, rh -> lh && rh }
    }
}