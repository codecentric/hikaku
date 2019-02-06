package de.codecentric.hikaku.converter.spring.endpoints

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

class SpringConverterEndpointTest {

    @Nested
    @WebMvcTest(RequestMappingDefinedOnClassImplicitlyAddingAllMethodsController::class)
    inner class RequestMappingDefinedOnClassImplicitlyAddingAllMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having implicit definition for all HTTP methods except TRACE is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", PUT),
                Endpoint("/todos", POST),
                Endpoint("/todos", DELETE),
                Endpoint("/todos", PATCH),
                Endpoint("/todos", HEAD),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos/tags", GET),
                Endpoint("/todos/tags", HEAD),
                Endpoint("/todos/tags", OPTIONS),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithExplicitGetMethodController::class)
    inner class RequestMappingDefinedOnClassWithExplicitGetMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having explicit GET method definition is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithGetMappingController::class)
    inner class RequestMappingDefinedOnClassWithGetMappingTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class using GetMapping annotation is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithExplicitPostMethodController::class)
    inner class RequestMappingDefinedOnClassWithExplicitPostMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having explicit POST method definition is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", POST),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithPostMappingController::class)
    inner class RequestMappingDefinedOnClassWithPostMappingTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class using PostMapping annotation is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", POST),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithExplicitPutMethodController::class)
    inner class RequestMappingDefinedOnClassWithExplicitPutMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having explicit PUT method definition is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", PUT),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithPutMappingController::class)
    inner class RequestMappingDefinedOnClassWithPutMappingTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class using PutMapping annotation is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", PUT),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithExplicitDeleteMethodController::class)
    inner class RequestMappingDefinedOnClassWithExplicitDeleteMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having explicit DELETE method definition is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", DELETE),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithDeleteMappingController::class)
    inner class RequestMappingDefinedOnClassWithDeleteMappingTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class using DeleteMapping annotation is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", DELETE),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithExplicitPatchMethodController::class)
    inner class RequestMappingDefinedOnClassWithExplicitPatchMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having explicit PATCH method definition is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", PATCH),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithPatchMappingController::class)
    inner class RequestMappingDefinedOnClassWithPatchMappingTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class using PatchMapping annotation is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", PATCH),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithExplicitHeadMethodController::class)
    inner class RequestMappingDefinedOnClassWithExplicitHeadMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having explicit HEAD method definition is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", HEAD),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithExplicitOptionsMethodController::class)
    inner class RequestMappingDefinedOnClassWithExplicitOptionsMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having explicit OPTIONS method definition is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnClassWithExplicitTraceMethodController::class)
    inner class RequestMappingDefinedOnClassWithExplicitTraceMethodTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on class having explicit TRACE method definition is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", TRACE),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(MultipleExplicitMappingAnnotationsAreNotSupportedBySpringController::class)
    inner class MultipleExplicitMappingAnnotationsAreNotSupportedBySpringTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `spring does not support multiple explicit annotations`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingSolelyOnFunctionController::class)
    inner class RequestMappingSolelyOnFunctionTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `RequestMapping annotation solely on function is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(MappingSolelyOnFunctionController::class)
    inner class MappingSolelyOnFunctionTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `Mapping annotation solely on function is extracted correctly`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", HEAD),
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
    @WebMvcTest(RequestMappingDefinedOnBothClassAndExplicitlyOnFunctionController::class)
    inner class RequestMappingDefinedOnBothClassAndExplicitlyOnFunctionTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint defined on both class using RequestMapping and on function using GetMapping creates a nested path`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todo/list", GET),
                Endpoint("/todo/list", OPTIONS),
                Endpoint("/todo/list", HEAD),
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
    @WebMvcTest(MultipleEndpointsDefinedOnGetMappingAnnotationController::class)
    inner class MultipleEndpointsDefinedOnGetMappingAnnotationTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `multiple endpoints on a mapping annotation create multiple Endpoint objects`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", HEAD),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todo/list", GET),
                Endpoint("/todo/list", HEAD),
                Endpoint("/todo/list", OPTIONS),
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
    @WebMvcTest(MultipleEndpointsDefinedOnRequestMappingAnnotationController::class)
    inner class MultipleEndpointsDefinedOnRequestMappingAnnotationTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `multiple endpoints on a mapping annotation create multiple Endpoint objects`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", HEAD),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todo/list", GET),
                Endpoint("/todo/list", HEAD),
                Endpoint("/todo/list", OPTIONS),
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
    @WebMvcTest(MultipleEndpointsAndMethodsDefinedOnRequestMappingAnnotationController::class)
    inner class MultipleEndpointsAndMethodsDefinedOnRequestMappingAnnotationTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `multiple endpoints and http methods on a mapping annotation create multiple Endpoint objects`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", POST),
                Endpoint("/todos", HEAD),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todo/list", GET),
                Endpoint("/todo/list", POST),
                Endpoint("/todo/list", HEAD),
                Endpoint("/todo/list", OPTIONS),
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
    @WebMvcTest(RequestMappingProvidingRegexForPathVariableController::class)
    inner class RequestMappingProvidingRegexForPathVariableTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint having regex on path parameter using RequestMapping converts to a path without the regex`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos/{id}",
                        httpMethod = GET,
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
                Endpoint("/todos/{id}", OPTIONS),
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
    @WebMvcTest(GetMappingProvidingRegexForPathVariableController::class)
    inner class GetMappingProvidingRegexForPathVariableTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `endpoint having regex on path parameter using GetMapping converts to a path without the regex`() {
            //given
            val specification: Set<Endpoint> = setOf(
                Endpoint(
                        path = "/todos/{id}",
                        httpMethod = GET,
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
                Endpoint("/todos/{id}", OPTIONS),
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