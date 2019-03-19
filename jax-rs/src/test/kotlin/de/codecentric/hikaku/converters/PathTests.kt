package de.codecentric.hikaku.converters

import de.codecentric.hikaku.converters.jaxrs.JaxRsConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PathTests {

    @Test
    fun `simple path`() {
        // given
        val specification = setOf(
                Endpoint("/todos", GET)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.path.simplepath").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `simple path without leading slash`() {
        // given
        val specification = setOf(
                Endpoint("/todos", GET)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.path.simplepathwithoutleadingslash").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `nested path`() {
        // given
        val specification = setOf(
                Endpoint("/todo/list", GET)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.path.nestedpath").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `nested path without leading slash`() {
        // given
        val specification = setOf(
                Endpoint("/todo/list", GET)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.path.nestedpathwithoutleadingslash").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `resource class is not detected, if there is no Path annotation on class level`() {
        //when
        val result = JaxRsConverter("test.jaxrs.path.nopathonclass").conversionResult

        //then
        assertThat(result).isEmpty()
    }
}