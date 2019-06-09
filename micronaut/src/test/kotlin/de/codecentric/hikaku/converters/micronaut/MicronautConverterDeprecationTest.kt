package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MicronautConverterDeprecationTest {

    @Test
    fun `no deprecation`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        deprecated =  false
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.deprecation.none").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `deprecated class`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        deprecated =  true
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.deprecation.onclass").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `deprecated function`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        deprecated = true
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.deprecation.onfunction").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}