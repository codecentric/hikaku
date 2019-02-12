package de.codecentric.hikaku.converter.spring.endpoints

import de.codecentric.hikaku.converter.spring.SpringConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import de.codecentric.hikaku.endpoints.PathParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE

class SpringConverterEndpointTest {

    @Nested
    inner class RequestMappingAnnotationTests {

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassImplicitlyAddingAllHttpMethodsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassImplicitlyAddingAllHttpMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having implicit definition for all HTTP methods except TRACE is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos/tags", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos/tags", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos/tags", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithExplicitGetMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithExplicitGetMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having explicit GET method definition is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithGetMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithGetMappingTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class using GetMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithExplicitPostMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithExplicitPostMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having explicit POST method definition is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithPostMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithPostMappingTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class using PostMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithExplicitPutMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithExplicitPutMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having explicit PUT method definition is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithPutMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithPutMappingTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class using PutMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithExplicitDeleteMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithExplicitDeleteMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having explicit DELETE method definition is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithDeleteMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithDeleteMappingTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class using DeleteMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithExplicitPatchMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithExplicitPatchMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having explicit PATCH method definition is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        )
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithPatchMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithPatchMappingTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class using PatchMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithExplicitHeadMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithExplicitHeadMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having explicit HEAD method definition is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithExplicitOptionsMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithExplicitOptionsMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having explicit OPTIONS method definition is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", OPTIONS, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassWithExplicitTraceMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassWithExplicitTraceMethodTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on class having explicit TRACE method definition is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", TRACE, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingOnlyOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnlyOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping annotation solely on function is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    inner class HttpMethodMappingAnnotationTests {

        @Nested
        @WebMvcTest(MultipleExplicitMappingAnnotationsAreNotSupportedBySpringController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleExplicitMappingAnnotationsAreNotSupportedBySpringTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `spring does not support multiple explicit annotations`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(MappingOnlyOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MappingOnlyOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `Mapping annotation solely on function is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    inner class MixedAnnotationTests {
        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassAndGetMappingOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingDefinedOnBothClassAndExplicitlyOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on both class using RequestMapping and on function using GetMapping creates a nested path`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todo/list", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todo/list", OPTIONS),
                        Endpoint("/todo/list", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE))
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    @WebMvcTest(MultipleEndpointsDefinedOnGetMappingAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class MultipleEndpointsDefinedOnGetMappingAnnotationTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `multiple endpoints on a mapping annotation create multiple Endpoint objects`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todos", OPTIONS),
                    Endpoint("/todo/list", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todo/list", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todo/list", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(MultipleEndpointsDefinedOnRequestMappingAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class MultipleEndpointsDefinedOnRequestMappingAnnotationTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `multiple endpoints on a mapping annotation create multiple Endpoint objects`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todos", OPTIONS),
                    Endpoint("/todo/list", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todo/list", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todo/list", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(MultipleEndpointsAndMethodsDefinedOnRequestMappingAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
    inner class MultipleEndpointsAndMethodsDefinedOnRequestMappingAnnotationTest {
        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `multiple endpoints and http methods on a mapping annotation create multiple Endpoint objects`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todos", POST, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todos", OPTIONS),
                    Endpoint("/todo/list", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todo/list", POST, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todo/list", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                    Endpoint("/todo/list", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(RequestMappingProvidingRegexForPathVariableController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
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
                            ),
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = HEAD,
                            pathParameters = setOf(
                                    PathParameter("id")
                            ),
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint("/todos/{id}", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    @WebMvcTest(GetMappingProvidingRegexForPathVariableController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
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
                            ),
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/todos/{id}",
                            httpMethod = HEAD,
                            pathParameters = setOf(
                                    PathParameter("id")
                            ),
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint("/todos/{id}", OPTIONS)
            )

            //when
            val implementation = SpringConverter(context)

            //then
            assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
        }
    }
}