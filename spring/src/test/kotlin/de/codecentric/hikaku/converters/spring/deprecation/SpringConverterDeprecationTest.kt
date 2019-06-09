package de.codecentric.hikaku.converters.spring.deprecation

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
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE

class SpringConverterDeprecationTest {

    @Nested
    @WebMvcTest(NoDeprecationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class NoDeprecationTest {

        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `no deprecation`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE),
                            deprecated = false
                    ),
                    Endpoint(
                            path = "/todos",
                            httpMethod = HEAD,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE),
                            deprecated = false
                    ),
                    Endpoint("/todos", OPTIONS, deprecated = false)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(DeprecatedClassController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class NoDeprecatedClassTest {

        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `deprecated class`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE),
                            deprecated = true
                    ),
                    Endpoint(
                            path = "/todos",
                            httpMethod = HEAD,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE),
                            deprecated = true
                    ),
                    Endpoint("/todos", OPTIONS, deprecated = true)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(DeprecatedFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class NoDeprecatedFunctionTest {

        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `deprcated function`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE),
                            deprecated = true
                    ),
                    Endpoint(
                            path = "/todos",
                            httpMethod = HEAD,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE),
                            deprecated = true
                    ),
                    Endpoint("/todos", OPTIONS, deprecated = true)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }
}