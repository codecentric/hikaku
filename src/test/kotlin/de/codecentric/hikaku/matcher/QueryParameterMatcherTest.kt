package de.codecentric.hikaku.matcher

import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.QueryParameter
import de.codecentric.hikaku.matcher.QueryParameterMatcher.matchQueryParameterName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class QueryParameterMatcherTest {

    @Test
    fun `Equal parameters in random order match`() {
        //given
        val specificationEndpoint = Endpoint(
                path = "/todos",
                queryParameter = setOf(
                        QueryParameter("c_param"),
                        QueryParameter("a_param"),
                        QueryParameter("b_param")
                )
        )

        val implementationEndpoint = Endpoint(
                path = "/todos",
                queryParameter = setOf(
                        QueryParameter("b_param"),
                        QueryParameter("c_param"),
                        QueryParameter("a_param")
                )
        )

        //when
        val result = matchQueryParameterName(specificationEndpoint, implementationEndpoint)

        //then
        assertThat(result).hasSize(3)

        assertThat(result[0].relatesTo).isEqualTo(Feature.QueryParameterName.toString())
        assertThat(result[0].specificationValue).isEqualTo("a_param")
        assertThat(result[0].implementationValue).isEqualTo("a_param")
        assertThat(result[0].matches).isTrue()

        assertThat(result[1].relatesTo).isEqualTo(Feature.QueryParameterName.toString())
        assertThat(result[1].specificationValue).isEqualTo("b_param")
        assertThat(result[1].implementationValue).isEqualTo("b_param")
        assertThat(result[1].matches).isTrue()

        assertThat(result[2].relatesTo).isEqualTo(Feature.QueryParameterName.toString())
        assertThat(result[2].specificationValue).isEqualTo("c_param")
        assertThat(result[2].implementationValue).isEqualTo("c_param")
        assertThat(result[2].matches).isTrue()
    }

    @Test
    fun `All parameters in random order differ`() {
        //given
        val specificationEndpoint = Endpoint(
                path = "/todos",
                queryParameter = setOf(
                        QueryParameter("c_param"),
                        QueryParameter("a_param"),
                        QueryParameter("b_param")
                )
        )

        val implementationEndpoint = Endpoint(
                path = "/todos",
                queryParameter = setOf(
                        QueryParameter("y_param"),
                        QueryParameter("z_param"),
                        QueryParameter("x_param")
                )
        )

        //when
        val result = matchQueryParameterName(specificationEndpoint, implementationEndpoint)

        //then
        assertThat(result).hasSize(3)

        assertThat(result[0].relatesTo).isEqualTo(Feature.QueryParameterName.toString())
        assertThat(result[0].specificationValue).isEqualTo("a_param")
        assertThat(result[0].implementationValue).isEqualTo("x_param")
        assertThat(result[0].matches).isFalse()

        assertThat(result[1].relatesTo).isEqualTo(Feature.QueryParameterName.toString())
        assertThat(result[1].specificationValue).isEqualTo("b_param")
        assertThat(result[1].implementationValue).isEqualTo("y_param")
        assertThat(result[1].matches).isFalse()

        assertThat(result[2].relatesTo).isEqualTo(Feature.QueryParameterName.toString())
        assertThat(result[2].specificationValue).isEqualTo("c_param")
        assertThat(result[2].implementationValue).isEqualTo("z_param")
        assertThat(result[2].matches).isFalse()
    }

    @Test
    fun `One missing parameter in specification`() {
        //given
        val specificationEndpoint = Endpoint(
                path = "/todos",
                queryParameter = setOf(
                        QueryParameter("c_param"),
                        QueryParameter("b_param")
                )
        )

        val implementationEndpoint = Endpoint(
                path = "/todos",
                queryParameter = setOf(
                        QueryParameter("b_param"),
                        QueryParameter("c_param"),
                        QueryParameter("a_param")
                )
        )

        //when
        val result = matchQueryParameterName(specificationEndpoint, implementationEndpoint)

        //then
        assertThat(result).hasSize(1)

        assertThat(result[0].specificationValue).isEqualTo(specificationEndpoint.queryParameter.size)
        assertThat(result[0].implementationValue).isEqualTo(implementationEndpoint.queryParameter.size)
        assertThat(result[0].matches).isFalse()
    }
}