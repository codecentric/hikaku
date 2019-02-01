package de.codecentric.hikaku.converter.openapi

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterHeaderParameterTest {

    @Test
    fun `check that header parameter are extracted correctly`() {
        //given
        val file = Paths.get(OpenApiConverterHeaderParameterTest::class.java.classLoader.getResource("openapi/header_parameter.yaml").toURI())
        val implementation: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                                QueryParameter("track-request", false),
                                QueryParameter("use-cache", true)
                        )
                )
        )

        //when
        val specification = OpenApiConverter(file).conversionResult

        //then
        assertThat(specification).isEqualTo(implementation)
    }
}