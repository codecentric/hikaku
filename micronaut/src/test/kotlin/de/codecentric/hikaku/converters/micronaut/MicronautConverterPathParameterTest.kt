package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.PathParameter
import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MicronautConverterPathParameterTest {

    @Test
    fun `path parameter defined by variable name`() {
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
        val result = MicronautConverter("test.micronaut.pathparameter.variable").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `path parameter defined by annotation using 'value'`() {
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
        val result = MicronautConverter("test.micronaut.pathparameter.annotation.value").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `path parameter defined by annotation using 'name'`() {
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
        val result = MicronautConverter("test.micronaut.pathparameter.annotation.name").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}