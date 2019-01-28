package de.codecentric.hikaku.matcher

import de.codecentric.hikaku.endpoints.HttpMethod

data class EndpointMatchResult(
        val httpMethodMatchResult: MatchResult<HttpMethod>,
        val pathMatchResult: MatchResult<String>,
        override val matchResults: List<MatchResult<*>>
) : MatchResultGroup {

    override fun matches(): Boolean {
        return super.matches() && httpMethodMatchResult.matches && pathMatchResult.matches
    }
}