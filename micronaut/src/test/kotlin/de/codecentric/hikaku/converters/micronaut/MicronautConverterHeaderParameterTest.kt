package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MicronautConverterHeaderParameterTest {

    @Test
    fun `optional header parameter`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                                HeaderParameter("allow-cache", false)
                        )
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.headerparameters.optional").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `required header parameter`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                                HeaderParameter("allow-cache", true)
                        )
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.headerparameters.required").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}