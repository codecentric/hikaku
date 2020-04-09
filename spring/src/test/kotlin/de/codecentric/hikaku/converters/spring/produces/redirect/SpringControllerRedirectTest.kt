package de.codecentric.hikaku.converters.spring.produces.redirect

import de.codecentric.hikaku.converters.spring.SpringConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ConfigurableApplicationContext

class SpringControllerRedirectTest {

    @Nested
    @WebMvcTest(RedirectTestController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class ReturnsRedirectViewTest {

        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `produces not set if the return type is RedirectView`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET, produces = emptySet()),
                Endpoint("/todos", HEAD, produces = emptySet()),
                Endpoint("/todos", OPTIONS, produces = emptySet())
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(RedirectUsingHttpServletResponseTestController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class RedirectUsingHttpServletResponseTest {

        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `produces is empty if there is no return type, because redirect is done using HttpServletResponse`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET, produces = emptySet()),
                Endpoint("/todos", HEAD, produces = emptySet()),
                Endpoint("/todos", OPTIONS, produces = emptySet())
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }
}
