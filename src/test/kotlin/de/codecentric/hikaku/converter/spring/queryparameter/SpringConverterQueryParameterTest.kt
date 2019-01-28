package de.codecentric.hikaku.converter.spring.queryparameter

import de.codecentric.hikaku.converter.spring.SpringConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import de.codecentric.hikaku.endpoints.QueryParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ConfigurableApplicationContext
import kotlin.test.assertFailsWith

class SpringConverterQueryParameterTest {

    @Nested
    @WebMvcTest(QueryParameterNamedByVariableController::class)
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
                        queryParameter = setOf(
                            QueryParameter("tag", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        queryParameter = setOf(
                                QueryParameter("tag", true)
                        )
                ),
                Endpoint("/error", GET),
                Endpoint("/error", PUT),
                Endpoint("/error", POST),
                Endpoint("/error", DELETE),
                Endpoint("/error", PATCH),
                Endpoint("/error", HEAD),
                Endpoint("/error", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context).conversionResult

            //then
            assertThat(implementation).isEqualTo(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterNamedByValueAttributeController::class)
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
                        queryParameter = setOf(
                            QueryParameter("tag", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        queryParameter = setOf(
                            QueryParameter("tag", true)
                        )
                ),
                Endpoint("/error", GET),
                Endpoint("/error", PUT),
                Endpoint("/error", POST),
                Endpoint("/error", DELETE),
                Endpoint("/error", PATCH),
                Endpoint("/error", HEAD),
                Endpoint("/error", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context).conversionResult

            //then
            assertThat(implementation).isEqualTo(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterNamedByNameAttributeController::class)
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
                        queryParameter = setOf(
                            QueryParameter("tag", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        queryParameter = setOf(
                            QueryParameter("tag", true)
                        )
                ),
                Endpoint("/error", GET),
                Endpoint("/error", PUT),
                Endpoint("/error", POST),
                Endpoint("/error", DELETE),
                Endpoint("/error", PATCH),
                Endpoint("/error", HEAD),
                Endpoint("/error", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context).conversionResult

            //then
            assertThat(implementation).isEqualTo(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterHavingBothNameAndValueAttributeController::class)
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
    @WebMvcTest(QueryParameterOptionalController::class)
    inner class QueryParameterOptionalTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `query parameter optional`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            queryParameter = setOf(
                                QueryParameter("tag", false)
                            )
                    ),
                    Endpoint("/todos", OPTIONS),
                    Endpoint(
                            path = "/todos",
                            httpMethod = HEAD,
                            queryParameter = setOf(
                                QueryParameter("tag", false)
                            )
                    ),
                    Endpoint("/error", GET),
                    Endpoint("/error", PUT),
                    Endpoint("/error", POST),
                    Endpoint("/error", DELETE),
                    Endpoint("/error", PATCH),
                    Endpoint("/error", HEAD),
                    Endpoint("/error", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context).conversionResult

            //then
            assertThat(implementation).isEqualTo(specification)
        }
    }

    @Nested
    @WebMvcTest(QueryParameterOptionalBecauseOfDefaultValueController::class)
    inner class QueryParameterOptionalBecauseOfDefaultValueTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `query parameter optional because of a default value`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        queryParameter = setOf(
                            QueryParameter("tag", false)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        queryParameter = setOf(
                            QueryParameter("tag", false)
                        )
                ),
                Endpoint("/error", GET),
                Endpoint("/error", PUT),
                Endpoint("/error", POST),
                Endpoint("/error", DELETE),
                Endpoint("/error", PATCH),
                Endpoint("/error", HEAD),
                Endpoint("/error", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context).conversionResult

            //then
            assertThat(implementation).isEqualTo(specification)
        }
    }
}