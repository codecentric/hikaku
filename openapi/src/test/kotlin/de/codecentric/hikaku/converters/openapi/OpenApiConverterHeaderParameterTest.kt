package de.codecentric.hikaku.converters.openapi

import de.codecentric.hikaku.endpoints.HeaderParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterHeaderParameterTest {

    @Test
    fun `header parameter inline declaration on Operation object`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("header_parameter/header_parameter_inline.yaml").toURI())
        val headerParameters = setOf(
                HeaderParameter("x-b3-traceid", false),
                HeaderParameter("allow-cache", true)
        )

        //when
        val result = OpenApiConverter(file).conversionResult.toList()[0].headerParameters

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(headerParameters)
    }

    @Test
    fun `one header parameter declared inline and one parameter referenced from parameters section in components`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("header_parameter/header_parameter_in_components.yaml").toURI())
        val headerParameters = setOf(
                HeaderParameter("x-b3-traceid", false),
                HeaderParameter("allow-cache", true)
        )

        //when
        val result = OpenApiConverter(file).conversionResult.toList()[0].headerParameters

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(headerParameters)
    }
}