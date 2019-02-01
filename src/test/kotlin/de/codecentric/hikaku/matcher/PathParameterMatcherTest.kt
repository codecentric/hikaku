package de.codecentric.hikaku.matcher

import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.PathParameter
import de.codecentric.hikaku.matcher.PathParameterMatcher.matchPathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PathParameterMatcherTest {

    @Test
    fun `Equal parameters in random order match`() {
        //given
        val specificationEndpoint = Endpoint(
                path = "/todos",
                pathParameters = setOf(
                        PathParameter("c_param"),
                        PathParameter("a_param"),
                        PathParameter("b_param")
                )
        )

        val implementationEndpoint = Endpoint(
                path = "/todos",
                pathParameters = setOf(
                        PathParameter("b_param"),
                        PathParameter("c_param"),
                        PathParameter("a_param")
                )
        )

        //when
        val result = matchPathParameter(specificationEndpoint, implementationEndpoint)

        //then
        assertThat(result).hasSize(3)

        assertThat(result[0].relatesTo).isEqualTo(Feature.PathParameter.toString())
        assertThat(result[0].specificationValue).isEqualTo("a_param")
        assertThat(result[0].implementationValue).isEqualTo("a_param")
        assertThat(result[0].matches).isTrue()

        assertThat(result[1].relatesTo).isEqualTo(Feature.PathParameter.toString())
        assertThat(result[1].specificationValue).isEqualTo("b_param")
        assertThat(result[1].implementationValue).isEqualTo("b_param")
        assertThat(result[1].matches).isTrue()

        assertThat(result[2].relatesTo).isEqualTo(Feature.PathParameter.toString())
        assertThat(result[2].specificationValue).isEqualTo("c_param")
        assertThat(result[2].implementationValue).isEqualTo("c_param")
        assertThat(result[2].matches).isTrue()
    }

    @Test
    fun `All parameters in random order differ`() {
        //given
        val specificationEndpoint = Endpoint(
                path = "/todos",
                pathParameters = setOf(
                        PathParameter("c_param"),
                        PathParameter("a_param"),
                        PathParameter("b_param")
                )
        )

        val implementationEndpoint = Endpoint(
                path = "/todos",
                pathParameters = setOf(
                        PathParameter("y_param"),
                        PathParameter("z_param"),
                        PathParameter("x_param")
                )
        )

        //when
        val result = matchPathParameter(specificationEndpoint, implementationEndpoint)

        //then
        assertThat(result).hasSize(3)

        assertThat(result[0].relatesTo).isEqualTo(Feature.PathParameter.toString())
        assertThat(result[0].specificationValue).isEqualTo("a_param")
        assertThat(result[0].implementationValue).isEqualTo("x_param")
        assertThat(result[0].matches).isFalse()

        assertThat(result[1].relatesTo).isEqualTo(Feature.PathParameter.toString())
        assertThat(result[1].specificationValue).isEqualTo("b_param")
        assertThat(result[1].implementationValue).isEqualTo("y_param")
        assertThat(result[1].matches).isFalse()

        assertThat(result[2].relatesTo).isEqualTo(Feature.PathParameter.toString())
        assertThat(result[2].specificationValue).isEqualTo("c_param")
        assertThat(result[2].implementationValue).isEqualTo("z_param")
        assertThat(result[2].matches).isFalse()
    }

    @Test
    fun `One missing parameter in specification`() {
        //given
        val specificationEndpoint = Endpoint(
                path = "/todos",
                pathParameters = setOf(
                        PathParameter("c_param"),
                        PathParameter("b_param")
                )
        )

        val implementationEndpoint = Endpoint(
                path = "/todos",
                pathParameters = setOf(
                        PathParameter("b_param"),
                        PathParameter("c_param"),
                        PathParameter("a_param")
                )
        )

        //when
        val result = matchPathParameter(specificationEndpoint, implementationEndpoint)

        //then
        assertThat(result).hasSize(1)

        assertThat(result[0].specificationValue).isEqualTo(specificationEndpoint.pathParameters.size)
        assertThat(result[0].implementationValue).isEqualTo(implementationEndpoint.pathParameters.size)
        assertThat(result[0].matches).isFalse()
    }
}