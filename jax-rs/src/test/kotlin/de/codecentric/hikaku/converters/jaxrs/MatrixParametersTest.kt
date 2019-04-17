package de.codecentric.hikaku.converters.jaxrs

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.MatrixParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MatrixParameterTest {

    @Test
    fun `matrix parameter on function`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        matrixParameters = setOf(
                                MatrixParameter("tag")
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.matrixparameters.onfunction").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}