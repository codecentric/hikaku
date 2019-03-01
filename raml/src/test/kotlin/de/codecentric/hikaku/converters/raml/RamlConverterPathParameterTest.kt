package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.HttpMethod.POST
import de.codecentric.hikaku.endpoints.PathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class RamlConverterPathParameterTest {

    @Test
    fun `simple path parameter declaration`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path_parameter/simple_path_parameter.raml").toURI())

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
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `nested path parameter declaration`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("path_parameter/nested_path_parameter.raml").toURI())

        val specification = setOf(
                Endpoint("/todos", POST),
                Endpoint(
                        path = "/todos/{id}",
                        httpMethod = GET,
                        pathParameters = setOf(
                                PathParameter("id")
                        )
                )
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }
}