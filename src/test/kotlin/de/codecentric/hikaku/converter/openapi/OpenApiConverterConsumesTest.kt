package de.codecentric.hikaku.converter.openapi

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterConsumesTest {

    @Test
    fun `inline declaration`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("openapi/consumes/consumes_inline.yaml").toURI())
        val implementation = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = POST,
                        consumes = setOf("application/xml")
                )
        )

        //when
        val specification = OpenApiConverter(file)

        //then
        assertThat(specification.conversionResult).containsExactlyInAnyOrderElementsOf(implementation)
    }

    @Test
    fun `response is declared in components section`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("openapi/consumes/consumes_requestbody_in_components.yaml").toURI())
        val implementation = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = POST,
                        consumes = setOf("application/xml")
                )
        )

        //when
        val specification = OpenApiConverter(file)

        //then
        assertThat(specification.conversionResult).containsExactlyInAnyOrderElementsOf(implementation)
    }
}