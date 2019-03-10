package de.codecentric.hikaku.converters.openapi

import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterQueryParameterTest {

    @Test
    fun `query parameter inline declaration on Operation object`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("query_parameter/query_parameter_inline.yaml").toURI())
        val queryParameters = setOf(
                QueryParameter("tag", false),
                QueryParameter("limit", true)
        )

        //when
        val result = OpenApiConverter(file).conversionResult.toList()[0].queryParameters

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(queryParameters)
    }

    @Test
    fun `one query parameter declared inline and one parameter referenced from parameters section in components`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("query_parameter/query_parameter_in_components.yaml").toURI())
        val queryParameters = setOf(
                QueryParameter("tag", false),
                QueryParameter("limit", true)
        )

        //when
        val result = OpenApiConverter(file).conversionResult.toList()[0].queryParameters

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(queryParameters)
    }
}