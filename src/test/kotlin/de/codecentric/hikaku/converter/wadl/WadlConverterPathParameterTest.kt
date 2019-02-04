package de.codecentric.hikaku.converter.wadl

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.PathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class WadlConverterPathParameterTest {

    @Test
    fun `check that path parameter are extracted correctly`() {
        //given
        val file = Paths.get(WadlConverterPathParameterTest::class.java.classLoader.getResource("wadl/path_parameter.wadl").toURI())
        val implementation: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos/{id}",
                        httpMethod = GET,
                        pathParameters = setOf(
                                PathParameter("id")
                        )
                )
        )

        //when
        val specification = WadlConverter(file).conversionResult

        //then
        assertThat(specification).isEqualTo(implementation)
    }
}