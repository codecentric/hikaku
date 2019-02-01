package de.codecentric.hikaku.converter.spring.headerparameter

import de.codecentric.hikaku.converter.spring.SpringConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ConfigurableApplicationContext
import kotlin.test.assertFailsWith

class SpringConverterHeaderParameterTest {

    @Nested
    @WebMvcTest(HeaderParameterNamedByVariableController::class)
    inner class HeaderParameterNamedByVariableTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `header parameter name defined by variable name`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                            HeaderParameter("useCache", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        headerParameters = setOf(
                                HeaderParameter("useCache", true)
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
    @WebMvcTest(HeaderParameterNamedByValueAttributeController::class)
    inner class HeaderParameterNamedByValueAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `header parameter name defined by attribute 'value'`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                            HeaderParameter("use-cache", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        headerParameters = setOf(
                            HeaderParameter("use-cache", true)
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
    @WebMvcTest(HeaderParameterNamedByNameAttributeController::class)
    inner class HeaderParameterNamedByNameAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `header parameter name defined by attribute 'name'`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                            HeaderParameter("use-cache", true)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        headerParameters = setOf(
                            HeaderParameter("use-cache", true)
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
    @WebMvcTest(HeaderParameterHavingBothNameAndValueAttributeController::class)
    inner class HeaderParameterHavingBothNameAndValueAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `both 'value' and 'name' attribute defined for header parameter`() {
            assertFailsWith<IllegalStateException> {
                SpringConverter(context).conversionResult
            }
        }
    }

    @Nested
    @WebMvcTest(HeaderParameterOptionalController::class)
    inner class HeaderParameterOptionalTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `header parameter optional`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            headerParameters = setOf(
                                HeaderParameter("use-cache", false)
                            )
                    ),
                    Endpoint("/todos", OPTIONS),
                    Endpoint(
                            path = "/todos",
                            httpMethod = HEAD,
                            headerParameters = setOf(
                                HeaderParameter("use-cache", false)
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
    @WebMvcTest(HeaderParameterOptionalBecauseOfDefaultValueController::class)
    inner class HeaderParameterOptionalBecauseOfDefaultValueTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `header parameter optional because of a default value`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        headerParameters = setOf(
                            HeaderParameter("tracker-id", false)
                        )
                ),
                Endpoint("/todos", OPTIONS),
                Endpoint(
                        path = "/todos",
                        httpMethod = HEAD,
                        headerParameters = setOf(
                            HeaderParameter("tracker-id", false)
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