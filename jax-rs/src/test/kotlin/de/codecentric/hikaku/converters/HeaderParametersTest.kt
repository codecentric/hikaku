package de.codecentric.hikaku.converters

import de.codecentric.hikaku.converters.jaxrs.JaxRsConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HeaderParameterTest {

    @Test
    fun `header parameter on function`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                                HeaderParameter("allow-cache")
                        )
                )
        )

        //when
        val result = JaxRsConverter("test.jaxrs.headerparameters.onfunction").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}