package de.codecentric.hikaku.converters.jaxrs

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.PathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PathParametersTest {

    @Test
    fun `no path parameter`() {
        //given
        val specification = setOf(
                Endpoint("/todos/{id}", GET)
        )

        //when
        val result = JaxRsConverter("test.jaxrs.pathparameters.nopathparameter").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `path parameter on function`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos/{id}",
                        httpMethod = GET,
                        pathParameters = setOf(
                                PathParameter("id")
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.pathparameters.onfunction").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}