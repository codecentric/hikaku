package de.codecentric.hikaku.converters

import de.codecentric.hikaku.converters.jaxrs.JaxRsConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HttpMethodsTest {

    @Test
    fun `extract all available http methods`() {
        //given
        val specification = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", DELETE),
                Endpoint("/todos", POST),
                Endpoint("/todos", PUT),
                Endpoint("/todos", PATCH),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.httpmethod.allmethods").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `resource class without http method annotation`() {
        //when
        val result = JaxRsConverter("test.jaxrs.httpmethod.noannotation").conversionResult

        //then
        assertThat(result).isEmpty()
    }
}