package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.HttpMethod.POST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class RamlConverterPathTest {

    @Test
    fun `simple path`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path/simple_path.raml").toURI())

        val specification = setOf(
                Endpoint("/todos", GET)
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `extract nested paths`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path/nested_path.raml").toURI())

        val specification = setOf(
                Endpoint("/todo", POST),
                Endpoint("/todo/list", GET)
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `extract nested paths defined in a single entry`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path/nested_path_single_entry.raml").toURI())

        val specification = setOf(
                Endpoint("/todo/list", POST),
                Endpoint("/todo/list", GET)
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }
}