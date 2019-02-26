package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class RamlConverterHttpMethodTest {

    @Test
    fun `extract all supported http methods`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("http_method/http_methods.raml").toURI())

        val specification = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", POST),
                Endpoint("/todos", PUT),
                Endpoint("/todos", PATCH),
                Endpoint("/todos", DELETE),
                Endpoint("/todos", HEAD),
                Endpoint("/todos", OPTIONS)
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `path without http methods does not create an endpoint`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("http_method/path_without_http_method.raml").toURI())

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).isEmpty()
    }
}