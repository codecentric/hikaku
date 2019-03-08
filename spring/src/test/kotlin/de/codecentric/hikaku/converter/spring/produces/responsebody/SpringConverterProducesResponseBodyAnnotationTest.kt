package de.codecentric.hikaku.converter.spring.produces.responsebody

import de.codecentric.hikaku.converter.spring.SpringConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.MediaType.*

class SpringConverterProducesResponseBodyAnnotationTest {

    @Nested
    inner class RequestMappingAnnotationTests {

        @Nested
        inner class ClassLevelTests {

            @Nested
            @WebMvcTest(RequestMappingOneMediaTypeIsInheritedByAllFunctionsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class OneMediaTypeIsInheritedByAllFunctionsTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `media type declared at class level using RequestMapping annotation is inherited by all functions`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = POST,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = HEAD,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PUT,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PATCH,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = DELETE,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint("/todos", OPTIONS),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = GET,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = POST,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = HEAD,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = PUT,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = PATCH,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = DELETE,
                                    produces = setOf(APPLICATION_XML_VALUE)
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
            @WebMvcTest(RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class MultipleMediaTypesAreInheritedByAllFunctionsTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `multiple media types declared at class level using RequestMapping annotation are inherited by all functions`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = POST,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = HEAD,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PUT,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PATCH,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = DELETE,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint("/todos", OPTIONS),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = GET,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = POST,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = HEAD,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = PUT,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = PATCH,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = DELETE,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
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
            @WebMvcTest(RequestMappingOnClassDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnClassUsingDefaultValueTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `no media type declared at class level will fallback to default media type`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = POST,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = HEAD,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PUT,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PATCH,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = DELETE,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint("/todos", OPTIONS)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingWithoutProducesOnClassInfoAndStringAsResponseBodyValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class NoConsumesInfoAndStringAsReturnValueTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `no media type declared and kotlin String as request body will lead to plain text`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = POST,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = HEAD,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PUT,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PATCH,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = DELETE,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint("/todos", OPTIONS)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingOnClassWithoutResponseBodyAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class NoResponseBodyAnnotationTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `no ResponseBody annotation results in an empty produces list`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", GET),
                            Endpoint("/todos", POST),
                            Endpoint("/todos", HEAD),
                            Endpoint("/todos", PUT),
                            Endpoint("/todos", PATCH),
                            Endpoint("/todos", DELETE),
                            Endpoint("/todos", OPTIONS)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }
        }

        @Nested
        inner class FunctionLevelTests {

            @Nested
            @WebMvcTest(RequestMappingOneMediaTypeIsExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class OneMediaTypeIsExtractedCorrectlyTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `media type declared at function level using RequestMapping annotation is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = POST,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = HEAD,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PUT,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PATCH,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = DELETE,
                                    produces = setOf(APPLICATION_XML_VALUE)
                            ),
                            Endpoint("/todos", OPTIONS)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingMultipleMediaTypesAreExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class MultipleMediaTypesAreExtractedCorrectlyTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `multiple media types declared at function level using RequestMapping annotation are extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = POST,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = HEAD,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PUT,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PATCH,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = DELETE,
                                    produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                            ),
                            Endpoint("/todos", OPTIONS)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingOnFunctionDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionUsingDefaultValueTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `no media type declared at function level will fallback to default media type`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = POST,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = HEAD,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PUT,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PATCH,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = DELETE,
                                    produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                            ),
                            Endpoint("/todos", OPTIONS)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingWithoutProducesOnFunctionInfoAndStringAsResponseBodyValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class NoConsumesInfoAndStringAsReturnValueTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `no media type declared and kotlin String as request body will lead to plain text`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = POST,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = HEAD,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PUT,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = PATCH,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = DELETE,
                                    produces = setOf(TEXT_PLAIN_VALUE)
                            ),
                            Endpoint("/todos", OPTIONS)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingOnFunctionWithoutResponseBodyAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class NoResponseBodyAnnotationTest {

                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `no ResponseBody annotation results in an empty produces list`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", GET),
                            Endpoint("/todos", POST),
                            Endpoint("/todos", HEAD),
                            Endpoint("/todos", PUT),
                            Endpoint("/todos", PATCH),
                            Endpoint("/todos", DELETE),
                            Endpoint("/todos", OPTIONS)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }
        }
    }

    @Nested
    inner class GetMappingAnnotationTests {

        @Nested
        @WebMvcTest(GetMappingOneMediaTypeIsExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at function level using GetMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = GET,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(GetMappingMultipleMediaTypesAreExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at function level using GetMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = GET,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(GetMappingDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnFunctionUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at function level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = GET,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(GetMappingWithoutProducesInfoAndStringAsResponseBodyValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoConsumesInfoAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared and kotlin String as request body will lead to plain text`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = GET,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(GetMappingWithoutResponseBodyAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoResponseBodyAnnotationTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no ResponseBody annotation results in an empty produces list`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", HEAD),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    inner class DeleteMappingAnnotationTests {

        @Nested
        @WebMvcTest(DeleteMappingOneMediaTypeIsExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at function level using DeleteMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = DELETE,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at function level using DeleteMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = DELETE,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnFunctionUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at function level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = DELETE,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingWithoutProducesInfoAndStringAsResponseBodyValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoConsumesInfoAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared and kotlin String as request body will lead to plain text`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = DELETE,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingWithoutResponseBodyAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoResponseBodyAnnotationTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no ResponseBody annotation results in an empty produces list`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE),
                        Endpoint("/todos", HEAD),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    inner class PatchMappingAnnotationTests {

        @Nested
        @WebMvcTest(PatchMappingOneMediaTypeIsExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at function level using PatchMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingMultipleMediaTypesAreExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at function level using PatchMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnFunctionUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at function level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingWithoutProducesInfoAndStringAsResponseBodyValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoConsumesInfoAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared and kotlin String as request body will lead to plain text`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingWithoutResponseBodyAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoResponseBodyAnnotationTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no ResponseBody annotation results in an empty produces list`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH),
                        Endpoint("/todos", HEAD),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    inner class PostMappingAnnotationTests {

        @Nested
        @WebMvcTest(PostMappingOneMediaTypeIsExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at function level using PostMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = POST,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingMultipleMediaTypesAreExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at function level using PostMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = POST,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnFunctionUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at function level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = POST,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingWithoutProducesInfoAndStringAsResponseBodyValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoConsumesInfoAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared and kotlin String as request body will lead to plain text`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = POST,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingWithoutResponseBodyAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoResponseBodyAnnotationTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no ResponseBody annotation results in an empty produces list`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST),
                        Endpoint("/todos", HEAD),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    inner class PutMappingAnnotationTests {

        @Nested
        @WebMvcTest(PutMappingOneMediaTypeIsExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at function level using PutMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PUT,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingMultipleMediaTypesAreExtractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExtractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at function level using PutMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PUT,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnFunctionUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at function level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PUT,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingWithoutProducesInfoAndStringAsResponseBodyValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoConsumesInfoAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared and kotlin String as request body will lead to plain text`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PUT,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingWithoutResponseBodyAnnotationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoResponseBodyAnnotationTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no ResponseBody annotation results in an empty produces list`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", HEAD),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    inner class OverwriteTests {

        @Nested
        @WebMvcTest(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by a declaration at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = GET,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = POST,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = PUT,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = DELETE,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = GET,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = POST,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = PUT,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = PATCH,
                                produces = setOf(APPLICATION_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = DELETE,
                                produces = setOf(APPLICATION_XML_VALUE)
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
        @WebMvcTest(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by a declaration at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = GET,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = POST,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = PUT,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = DELETE,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = GET,
                                produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = POST,
                                produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = HEAD,
                                produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = PUT,
                                produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = PATCH,
                                produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)
                        ),
                        Endpoint(
                                path = "/todos/{id}",
                                httpMethod = DELETE,
                                produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)
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
        @WebMvcTest(GetMappingOneMediaTypeIsOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class GetMappingOneMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by GetMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = GET,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingOneMediaTypeIsOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DeleteMappingOneMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by DeleteMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = DELETE,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingOneMediaTypeIsOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PatchMappingOneMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by PatchMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingOneMediaTypeIsOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PostMappingOneMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by PostMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = POST,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingOneMediaTypeIsOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PutMappingOneMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by PutMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PUT,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(GetMappingMultipleMediaTypesAreOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class GetMappingMultipleMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by GetMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = GET,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingMultipleMediaTypesAreOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DeleteMappingMultipleMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by DeleteMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = DELETE,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingMultipleMediaTypesAreOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PatchMappingMultipleMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by PatchMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PATCH,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingMultipleMediaTypesAreOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PostMappingMultipleMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by PostMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = POST,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingMultipleMediaTypesAreOverwrittenController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PutMappingMultipleMediaTypeIsOverwrittenTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by PutMapping at function level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint(
                                path = "/todos",
                                httpMethod = PUT,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint(
                                path = "/todos",
                                httpMethod = HEAD,
                                produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)
                        ),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }
    }

    @Nested
    @WebMvcTest(ErrorEndpointController::class)
    inner class MediaTypeIsNotAddedToDefaultErrorEndpoint {

        @Autowired
        lateinit var context: ConfigurableApplicationContext

        @Test
        fun `error endpoint does not provide the same media type`() {
            //given
            val specification: Set<Endpoint> = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(APPLICATION_XML_VALUE)
                    ),
                    Endpoint(
                            path = "/todos",
                            httpMethod = HEAD,
                            produces = setOf(APPLICATION_XML_VALUE)
                    ),
                    Endpoint("/todos", OPTIONS),
                    Endpoint(
                            path = "/error",
                            httpMethod = GET,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = POST,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = HEAD,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = PUT,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = PATCH,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = DELETE,
                            produces = setOf(APPLICATION_JSON_UTF8_VALUE)
                    ),
                    Endpoint("/error", OPTIONS),
                    Endpoint(
                            path = "/error",
                            httpMethod = GET,
                            produces = setOf(TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = POST,
                            produces = setOf(TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = HEAD,
                            produces = setOf(TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = PUT,
                            produces = setOf(TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = PATCH,
                            produces = setOf(TEXT_HTML_VALUE)
                    ),
                    Endpoint(
                            path = "/error",
                            httpMethod = DELETE,
                            produces = setOf(TEXT_HTML_VALUE)
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