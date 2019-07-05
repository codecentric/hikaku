package de.codecentric.hikaku.converters.openapi

import de.codecentric.hikaku.endpoints.PathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterPathParameterTest {

    @Test
    fun `path parameter inline declaration on Operation object`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path_parameter/path_parameter_inline.yaml").toURI())
        val pathParameter = PathParameter("id")

        //when
        val result = OpenApiConverter(file).conversionResult.toList()

        //then
        assertThat(result[0].pathParameters).containsExactly(pathParameter)
        assertThat(result[1].pathParameters).containsExactly(pathParameter)
        assertThat(result[2].pathParameters).containsExactly(pathParameter)
    }

    @Test
    fun `one path parameter declared inline and two parameters referenced from parameters section in components`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path_parameter/path_parameter_in_components.yaml").toURI())
        val pathParameter = PathParameter("id")

        //when
        val result = OpenApiConverter(file).conversionResult.toList()

        //then
        assertThat(result[0].pathParameters).containsExactly(pathParameter)
        assertThat(result[1].pathParameters).containsExactly(pathParameter)
        assertThat(result[2].pathParameters).containsExactly(pathParameter)
    }

    @Test
    fun `common path parameter inline declaration`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path_parameter/common_path_parameter_inline.yaml").toURI())
        val pathParameter = PathParameter("id")

        //when
        val result = OpenApiConverter(file).conversionResult.toList()

        //then
        assertThat(result[0].pathParameters).containsExactly(pathParameter)
        assertThat(result[1].pathParameters).containsExactly(pathParameter)
        assertThat(result[2].pathParameters).containsExactly(pathParameter)
    }

    @Test
    fun `common path parameter referenced from parameters section in components`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path_parameter/common_path_parameter_in_components.yaml").toURI())
        val pathParameter = PathParameter("id")

        //when
        val result = OpenApiConverter(file).conversionResult.toList()

        //then
        assertThat(result[0].pathParameters).containsExactly(pathParameter)
        assertThat(result[1].pathParameters).containsExactly(pathParameter)
        assertThat(result[2].pathParameters).containsExactly(pathParameter)
    }
}