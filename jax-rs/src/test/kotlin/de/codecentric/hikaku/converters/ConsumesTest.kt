package de.codecentric.hikaku.converters

import de.codecentric.hikaku.converters.jaxrs.JaxRsConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConsumesTest {

    @Test
    fun `single media type defined on class`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        consumes = setOf(
                                "application/json"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.consumes.singlemediatypeonclass").conversionResult

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
                        consumes = setOf(
                                "application/json"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.consumes.singlemediatypeonfunction").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `single media type without request body`() {
        // given
        val specification = setOf(
                Endpoint( "/todos", GET)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.consumes.singlemediatypewithoutrequestbody").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `request body, but no annotation`() {
        // given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        consumes = setOf(
                                "*/*"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.consumes.noannotation").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `no request body, but other annotated parameter`() {
        // given
        val specification = setOf(
                Endpoint("/todos", GET)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.consumes.singlemediatypewithoutrequestbodybutotherannotatedparameter").conversionResult

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
                        consumes = setOf(
                                "application/json",
                                "application/xml"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.consumes.multiplemediatypesonclass").conversionResult

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
                        consumes = setOf(
                                "application/json",
                                "application/xml"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.consumes.multiplemediatypesonfunction").conversionResult

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
                        consumes = setOf(
                                "application/json",
                                "text/plain"
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.consumes.functiondeclarationoverwritesclassdeclaration").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}