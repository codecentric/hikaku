package de.codecentric.hikaku.converter.wadl

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class WadlConverterQueryParameterTest {

    @Test
    fun `check that query parameter are extracted correctly`() {
        //given
        val file = Paths.get(WadlConverterQueryParameterTest::class.java.classLoader.getResource("wadl/query_parameter.wadl").toURI())
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
        val specification = WadlConverter(file).conversionResult

        //then
        assertThat(specification).containsAll(implementation)
    }
}