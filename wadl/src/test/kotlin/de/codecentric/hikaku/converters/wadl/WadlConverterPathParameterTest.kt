package de.codecentric.hikaku.converters.wadl

import de.codecentric.hikaku.endpoints.PathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths


class WadlConverterPathParameterTest {

    @Test
    fun `check that path parameter are extracted correctly`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path_parameters.wadl").toURI())
        val pathParameter = PathParameter("id")

        //when
        val specification = WadlConverter(file)

        //then
        val resultingPathParameters = specification.conversionResult.toList()[0].pathParameters
        assertThat(resultingPathParameters).containsExactly(pathParameter)
    }
}