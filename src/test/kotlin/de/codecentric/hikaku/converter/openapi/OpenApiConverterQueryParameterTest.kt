package de.codecentric.hikaku.converter.openapi

import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterQueryParameterTest {

    @Test
    fun `check that query parameter are extracted correctly`() {
        //given
        val file = Paths.get(OpenApiConverterQueryParameterTest::class.java.classLoader.getResource("openapi/query_parameter.yaml").toURI())
        val queryParameters = setOf(
                QueryParameter("tag", false),
                QueryParameter("limit", true)
        )

        //when
        val specification = OpenApiConverter(file)

        //then
        val resultingQueryParameters = specification.conversionResult.toList()[0].queryParameters
        assertThat(resultingQueryParameters).containsExactlyInAnyOrderElementsOf(queryParameters)
    }
}