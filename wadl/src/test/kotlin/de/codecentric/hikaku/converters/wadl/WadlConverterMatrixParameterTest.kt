package de.codecentric.hikaku.converters.wadl

import de.codecentric.hikaku.endpoints.MatrixParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class WadlConverterMatrixParameterTest {

    @Test
    fun `check that matrix parameter are extracted correctly`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("matrix_parameters.wadl").toURI())
        val matrixParameters = setOf(
                MatrixParameter("done", false),
                MatrixParameter("tag", true)
        )

        //when
        val specification = WadlConverter(file)

        //then
        val resultingMatrixParameters = specification.conversionResult.toList()[0].matrixParameters
        assertThat(resultingMatrixParameters).containsExactlyInAnyOrderElementsOf(matrixParameters)
    }
}