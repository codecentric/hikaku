package de.codecentric.hikaku.converter.spring.pathparameter

import de.codecentric.hikaku.converter.spring.SpringConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import de.codecentric.hikaku.endpoints.PathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ConfigurableApplicationContext
import kotlin.test.assertFailsWith

class SpringConverterPathParameterTest {

    @Nested
    @WebMvcTest(PathParameterNamedByVariableController::class)
    inner class PathParameterNamedByVariableTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `path parameter name defined by variable name`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = GET,
                            pathParameters = setOf(
                                PathParameter("id")
                            )
                    ),
                    Endpoint("/todos/{id}", OPTIONS),
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = HEAD,
                            pathParameters = setOf(
                                PathParameter("id")
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
            assertThat(implementation).containsAll(specification)
        }
    }

    @Nested
    @WebMvcTest(PathParameterNamedByValueAttributeController::class)
    inner class PathParameterNamedByValueAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `path parameter name defined by 'value' attribute`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = GET,
                            pathParameters = setOf(
                                    PathParameter("id")
                            )
                    ),
                    Endpoint("/todos/{id}", OPTIONS),
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = HEAD,
                            pathParameters = setOf(
                                    PathParameter("id")
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
            assertThat(implementation).containsAll(specification)
        }
    }

    @Nested
    @WebMvcTest(PathParameterNamedByNameAttributeController::class)
    inner class PathParameterNamedByNameAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `path parameter name defined by 'name' attribute`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = GET,
                            pathParameters = setOf(
                                    PathParameter("id")
                            )
                    ),
                    Endpoint("/todos/{id}", OPTIONS),
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = HEAD,
                            pathParameters = setOf(
                                    PathParameter("id")
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
            assertThat(implementation).containsAll(specification)
        }
    }

    @Nested
    @WebMvcTest(PathParameterHavingBorthValueAndNameAttributeController::class)
    inner class PathParameterHavingBorthValueAndNameAttributeTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `path parameter name defined by 'name' attribute`() {
            assertFailsWith<IllegalStateException> {
                SpringConverter(context).conversionResult
            }
        }
    }

    @Nested
    @WebMvcTest(PathParameterSupportedForOptionsIfExplicitlyDefinedController::class)
    inner class PathParameterSupportedForOptionsIfExplicitlyDefinedTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `path parameter are supported for OPTIONS if defined explicitly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = OPTIONS,
                            pathParameters = setOf(
                                    PathParameter("id")
                            )
                    ),
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = HEAD,
                            pathParameters = setOf(
                                    PathParameter("id")
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
            assertThat(implementation).containsAll(specification)
        }
    }
}