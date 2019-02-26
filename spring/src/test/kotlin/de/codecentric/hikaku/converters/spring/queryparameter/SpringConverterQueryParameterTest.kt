package de.codecentric.hikaku.converters.spring.queryparameter

import de.codecentric.hikaku.converters.spring.SpringConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.MediaType
import kotlin.test.assertFailsWith

class SpringConverterQueryParameterTest {

    @Nested
    @WebMvcTest(QueryParameterNamedByVariableController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class QueryParameterNamedByVariableTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `query parameter name defined by variable name`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                            QueryParameter("tag", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        queryParameters = setOf(
                                QueryParameter("tag", true)
                        )
                )
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterNamedByValueAttributeController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class QueryParameterNamedByValueAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `query parameter name defined by attribute 'value'`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                            QueryParameter("tag", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        queryParameters = setOf(
                            QueryParameter("tag", true)
                        )
                )
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterNamedByNameAttributeController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class QueryParameterNamedByNameAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `query parameter name defined by attribute 'name'`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                            QueryParameter("tag", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        queryParameters = setOf(
                            QueryParameter("tag", true)
                        )
                )
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterHavingBothNameAndValueAttributeController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class QueryParameterHavingBothNameAndValueAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `both 'value' and 'name' attribute defined for query parameter`() {
            assertFailsWith<IllegalStateException> {
                SpringConverter(context).conversionResult
            }
        }
    }

    @Nested
    @WebMvcTest(QueryParameterOptionalController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class QueryParameterOptionalTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `query parameter set to optional`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            queryParameters = setOf(
                                QueryParameter("tag", false)
                            )
                    ),
                    Endpoint("/todos", OPTIONS),
                    Endpoint(
                            path = "/todos",
                            httpMethod = HEAD,
                            queryParameters = setOf(
                                QueryParameter("tag", false)
                            )
                    )
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterOptionalBecauseOfDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class QueryParameterOptionalBecauseOfDefaultValueTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `query parameter optional, because of a default value`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameters = setOf(
                            QueryParameter("tag", false)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        queryParameters = setOf(
                            QueryParameter("tag", false)
                        )
                )
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterOnDefaultErrorEndpointController::class)
    inner class QueryParameterOnDefaultErrorEndpointTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `query parameters are not added to default error endpoint`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            queryParameters = setOf(
                                    QueryParameter("tag")
                            )
                    ),
                    Endpoint("/todos", OPTIONS),
                    Endpoint(
                            path = "/todos",
                            httpMethod = HEAD,
                            queryParameters = setOf(
                                    QueryParameter("tag")
                            )
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = GET,
                            produces = setOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = POST,
                            produces = setOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = HEAD,
                            produces = setOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = PUT,
                            produces = setOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = PATCH,
                            produces = setOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = DELETE,
                            produces = setOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint("/error", OPTIONS),
                    Endpoint(
                            path = "/error",
                            httpMethod = GET,
                            produces = setOf(MediaType.TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = POST,
                            produces = setOf(MediaType.TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = HEAD,
                            produces = setOf(MediaType.TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = PUT,
                            produces = setOf(MediaType.TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = PATCH,
                            produces = setOf(MediaType.TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = DELETE,
                            produces = setOf(MediaType.TEXT_HTML_VALUE)
                    ),
                    Endpoint("/error", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }
}