package de.codecentric.hikaku.converter.wadl

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class WadlConverterProducesTest {

    @Test
    fun `check that media type information for the response are extracted correctly`() {
        //given
        val file = Paths.get(WadlConverterQueryParameterTest::class.java.classLoader.getResource("wadl/produces.wadl").toURI())
        val implementation: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf(
                                "application/json",
                                "application/xml",
                                "text/plain"
                        )
                )
        )

        //when
        val specification = WadlConverter(file).conversionResult

        //then
        assertThat(specification).isEqualTo(implementation)
    }
}