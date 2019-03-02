package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class RamlConverterHeaderParameterTest {

    @Test
    fun `extract an optional and a required header parameter`() {
        //given
        val file = Paths.get(this::class.java.classLoader.getResource("header_parameter.raml").toURI())

        val specification = setOf(
                Endpoint(
                        path ="/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                                HeaderParameter("allow-cache", true),
                                HeaderParameter("x-b3-traceid", false)
                        )
                )
        )

        //when
        val implementation = RamlConverter(file).conversionResult

        //then
        assertThat(implementation).containsExactlyInAnyOrderElementsOf(specification)
    }
}