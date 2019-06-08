package de.codecentric.hikaku.converters.openapi

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterDeprecationTest {

    @Test
    fun `no deprecation`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("deprecation/deprecation_none.yaml").toURI())
        val implementation = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf("application/json"),
                        deprecated = false
                )
        )

        //when
        val specification = OpenApiConverter(file)

        //then
        assertThat(specification.conversionResult).containsExactlyInAnyOrderElementsOf(implementation)
    }

    @Test
    fun `deprecated class`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("deprecation/deprecation_operation.yaml").toURI())
        val implementation = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf("application/json"),
                        deprecated = true
                )
        )

        //when
        val specification = OpenApiConverter(file)

        //then
        assertThat(specification.conversionResult).containsExactlyInAnyOrderElementsOf(implementation)
    }

    @Test
    fun `deprecated function`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("deprecation/deprecation_operation.yaml").toURI())
        val implementation = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf("application/json"),
                        deprecated = true
                )
        )

        //when
        val specification = OpenApiConverter(file)

        //then
        assertThat(specification.conversionResult).containsExactlyInAnyOrderElementsOf(implementation)
    }
}