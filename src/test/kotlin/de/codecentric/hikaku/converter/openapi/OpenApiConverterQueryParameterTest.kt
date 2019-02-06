package de.codecentric.hikaku.converter.openapi

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterQueryParameterTest {

    @Test
    fun `check that query parameter are extracted correctly`() {
        //given
        val file = Paths.get(OpenApiConverterQueryParameterTest::class.java.classLoader.getResource("openapi/query_parameter.yaml").toURI())
        val implementation: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                                QueryParameter("tag", false),
                                QueryParameter("limit", true)
                        )
                )
        )

        //when
        val specification = OpenApiConverter(file).conversionResult

        //then
        assertThat(specification).containsAll(implementation)
    }
}