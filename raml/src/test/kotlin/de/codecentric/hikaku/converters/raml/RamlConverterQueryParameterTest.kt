package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class RamlConverterQueryParameterTest {

    @Test
    fun `extracts required and optional query parameter`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("query_parameter/query_parameter.raml").toURI())

        val specification = setOf(
                Endpoint(
                        path ="/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                                QueryParameter("limit", true),
                                QueryParameter("tag", false)
                        )
                )
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }
}