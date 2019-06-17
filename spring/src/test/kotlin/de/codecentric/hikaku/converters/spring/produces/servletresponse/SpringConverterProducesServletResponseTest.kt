package de.codecentric.hikaku.converters.spring.produces.servletresponse

import de.codecentric.hikaku.converters.spring.SpringConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ConfigurableApplicationContext

class SpringConverterProducesServletResponseTest {

    @Nested
    inner class GetMappingAnnotationTests {

        @Nested
        @WebMvcTest(ProducesServletResponseTestController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoProducesInfoAndNoReturnTypeTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type and response servlet argument declared and no return type results in proper media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/test", GET, produces = setOf("text/plain")),
                        Endpoint("/test", HEAD, produces = setOf("text/plain")),
                        Endpoint("/test", OPTIONS, produces = setOf())
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }
}