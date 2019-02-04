package de.codecentric.hikaku.converter.wadl

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class WadlConverterHeaderParameterTest {

    @Test
    fun `check that header parameter are extracted correctly`() {
        //given
        val file = Paths.get(WadlConverterHeaderParameterTest::class.java.classLoader.getResource("wadl/header_parameter.wadl").toURI())
        val implementation: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                                HeaderParameter("x-b3-traceid", false),
                                HeaderParameter("use-cache", true)
                        )
                )
        )

        //when
        val specification = WadlConverter(file).conversionResult

        //then
        assertThat(specification).isEqualTo(implementation)
    }
}