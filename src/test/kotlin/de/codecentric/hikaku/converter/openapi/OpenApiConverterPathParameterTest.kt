package de.codecentric.hikaku.converter.openapi

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import de.codecentric.hikaku.endpoints.PathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterPathParameterTest {

    @Test
    fun `check that path parameter are extracted correctly`() {
        //given
        val file = Paths.get(OpenApiConverterPathParameterTest::class.java.classLoader.getResource("openapi/path_parameter.yaml").toURI())
        val implementation: Set<Endpoint> = setOf(
            Endpoint(
                    path = "/todos/{id}",
                    httpMethod = GET,
                    pathParameters = setOf(
                            PathParameter("id")
                    )
            ),
            Endpoint(
                    path = "/todos/{id}",
                    httpMethod = DELETE,
                    pathParameters = setOf(
                            PathParameter("id")
                    )
            ),
            Endpoint(
                    path = "/todos/{id}",
                    httpMethod = POST,
                    pathParameters = setOf(
                            PathParameter("id")
                    )
            )
        )

        //when
        val specification = OpenApiConverter(file).conversionResult

        //then
        assertThat(specification).containsAll(implementation)
    }
}