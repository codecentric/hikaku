package de.codecentric.hikaku.converters.jaxrs

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JaxRsConverterProducesTest {

    @Test
    fun `single media type defined on class`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf(
                                "application/json"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.produces.singlemediatypeonclass").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `single media type defined on function`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf(
                                "application/json"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.produces.singlemediatypeonfunction").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `single media type without return type`() {
        // given
        val specification = setOf(
                Endpoint( "/todos", GET)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.produces.singlemediatypewithoutreturntype").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `return type, but no annotation`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf(
                                "*/*"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.produces.noannotation").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `multiple media type defined on class`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf(
                                "application/json",
                                "application/xml"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.produces.multiplemediatypesonclass").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `multiple media type defined on function`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf(
                                "application/json",
                                "application/xml"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.produces.multiplemediatypesonfunction").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `function declaration overwrites class declaration`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf(
                                "application/json",
                                "text/plain"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.produces.functiondeclarationoverwritesclassdeclaration").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}