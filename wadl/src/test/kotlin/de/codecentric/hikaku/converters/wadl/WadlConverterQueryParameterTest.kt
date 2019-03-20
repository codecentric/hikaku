package de.codecentric.hikaku.converters.wadl

import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class WadlConverterQueryParameterTest {

    @Test
    fun `check that query parameter are extracted correctly`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("query_parameters.wadl").toURI())
        val queryParameters = setOf(
                QueryParameter("tag", false),
                QueryParameter("limit", true)
        )

        //when
        val specification = WadlConverter(file)

        //then
        val resultingQueryParameters = specification.conversionResult.toList()[0].queryParameters
        assertThat(resultingQueryParameters).containsExactlyInAnyOrderElementsOf(queryParameters)
    }
}