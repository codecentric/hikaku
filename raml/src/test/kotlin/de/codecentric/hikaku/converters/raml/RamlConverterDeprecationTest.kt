package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class RamlConverterDeprecationTest {

    @Test
    fun `no deprecations`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("deprecation/none.raml").toURI())

        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf("text/plain"),
                        deprecated = false
                )
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `deprecated resource`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("deprecation/on_resource.raml").toURI())

        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf("text/plain"),
                        deprecated = true
                )
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `deprecated method`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("deprecation/on_method.raml").toURI())

        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf("text/plain"),
                        deprecated = true
                )
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }
}