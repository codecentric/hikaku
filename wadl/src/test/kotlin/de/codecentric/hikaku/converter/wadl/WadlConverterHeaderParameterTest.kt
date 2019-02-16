package de.codecentric.hikaku.converter.wadl

import de.codecentric.hikaku.endpoints.HeaderParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class WadlConverterHeaderParameterTest {

    @Test
    fun `check that header parameter are extracted correctly`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("header_parameter.wadl").toURI())
        val headerParameters = setOf(
                HeaderParameter("x-b3-traceid", false),
                HeaderParameter("use-cache", true)
        )

        //when
        val specification = WadlConverter(file)

        //then
        val resultingHeaderParameters = specification.conversionResult.toList()[0].headerParameters
        assertThat(resultingHeaderParameters).containsExactlyInAnyOrderElementsOf(headerParameters)
    }
}