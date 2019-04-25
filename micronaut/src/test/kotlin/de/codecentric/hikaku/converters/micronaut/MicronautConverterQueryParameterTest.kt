package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MicronautConverterQueryParameterTest {

    @Test
    fun `query parameter required`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                                QueryParameter("filter", true)
                        )
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.queryparameters.required.annotation").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `query parameter optional, because a default value exists`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                                QueryParameter("filter", false)
                        )
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.queryparameters.optional").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `query is required and defined without annotation, because no matching template exists in url`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                                QueryParameter("filter", false)
                        )
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.queryparameters.required.withoutannotation").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}