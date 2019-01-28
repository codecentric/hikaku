package de.codecentric.hikaku.matcher

import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.HttpMethod.POST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EndpointMatchResultTest {

    @Test
    fun `Everything matches`() {
        //given
        val endpointMatchResult = EndpointMatchResult(
                httpMethodMatchResult = MatchResult("http method", GET, GET),
                pathMatchResult = MatchResult("path", "/todos", "/todos"),
                matchResults = listOf(
                        MatchResult("query parameter name", "filter", "filter")
                )
        )

        //when
        val matches = endpointMatchResult.matches()

        //then
        assertThat(matches).isTrue()
    }

    @Test
    fun `http method does not match, but everything else does`() {
        //given
        val endpointMatchResult = EndpointMatchResult(
            httpMethodMatchResult = MatchResult("http method", GET, POST),
            pathMatchResult = MatchResult("path", "/todos", "/todos"),
            matchResults = listOf(
                    MatchResult("query parameter name", "filter", "filter")
            )
        )

        //when
        val matches = endpointMatchResult.matches()

        //then
        assertThat(matches).isFalse()
    }

    @Test
    fun `path does not match, but everything else does`() {
        //given
        val endpointMatchResult = EndpointMatchResult(
                httpMethodMatchResult = MatchResult("http method", GET, GET),
                pathMatchResult = MatchResult("path", "/todos", "/todos/{id}"),
                matchResults = listOf(
                        MatchResult("path parameter", "id", "id")
                )
        )

        //when
        val matches = endpointMatchResult.matches()

        //then
        assertThat(matches).isFalse()
    }

    @Test
    fun `feature does not match, but everything else does`() {
        //given
        val endpointMatchResult = EndpointMatchResult(
                httpMethodMatchResult = MatchResult("http method", GET, GET),
                pathMatchResult = MatchResult("path", "/todos/{id}", "/todos/{id}"),
                matchResults = listOf(
                        MatchResult("path parameter", "id", "todoId")
                )
        )

        //when
        val matches = endpointMatchResult.matches()

        //then
        assertThat(matches).isFalse()
    }
}