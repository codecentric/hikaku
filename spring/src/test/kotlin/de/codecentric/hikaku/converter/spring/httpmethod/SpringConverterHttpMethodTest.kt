package de.codecentric.hikaku.converter.spring.httpmethod

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

class HttpMethodTestController {

    @Nested
    inner class RequestMappingTests {

        @Nested
        inner class ClassLevelTests {

            @Nested
            @WebMvcTest(RequestMappingDefinedOnClassWithExplicitGetMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnClassWithExplicitGetMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit GET method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", GET),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
                            Endpoint("/todos", POST),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
                            Endpoint("/todos", PUT),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
                            Endpoint("/todos", PATCH),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
                            Endpoint("/todos", DELETE),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
                            Endpoint("/todos", TRACE),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnClassNoHttpMethodDefinedController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class NoHttpMethodDefinedTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `no http method defined means that spring supports all http methods except for trace`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", GET),
                            Endpoint("/todos", PUT),
                            Endpoint("/todos", POST),
                            Endpoint("/todos", DELETE),
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

            @Nested
            @WebMvcTest(RequestMappingDefinedOnClassWithMultipleMethodsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnClassWithMultipleMethodsTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint providing multiple http methods`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", PUT),
                            Endpoint("/todos", PATCH),
                            Endpoint("/todos", TRACE),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
            @WebMvcTest(RequestMappingDefinedOnFunctionWithExplicitGetMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionWithExplicitGetMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit GET method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", GET),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionWithExplicitPostMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionWithExplicitPostMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit POST method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", POST),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionWithExplicitHeadMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionWithExplicitHeadMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit HEAD method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionWithExplicitPutMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionWithExplicitPutMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit PUT method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", PUT),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionWithExplicitPatchMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionWithExplicitPatchMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit PATCH method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", PATCH),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionWithExplicitDeleteMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionWithExplicitDeleteMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit DELETE method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", DELETE),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionWithExplicitTraceMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionWithExplicitTraceMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit TRACE method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", TRACE),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionWithExplicitOptionsMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnFunctionWithExplicitOptionsMethodTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint defined on class having explicit OPTIONS method definition is extracted correctly`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
                    )

                    //when
                    val implementation = SpringConverter(context)

                    //then
                    assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
                }
            }

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionNoHttpMethodDefinedController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class NoHttpMethodDefinedTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `no http method defined means that spring supports all http methods except for trace`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", GET),
                            Endpoint("/todos", PUT),
                            Endpoint("/todos", POST),
                            Endpoint("/todos", DELETE),
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

            @Nested
            @WebMvcTest(RequestMappingDefinedOnFunctionWithMultipleMethodsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class DefinedOnClassWithMultipleMethodsTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `endpoint providing multiple http methods`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", PUT),
                            Endpoint("/todos", PATCH),
                            Endpoint("/todos", TRACE),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
    inner class HttpMethodMappingAnnotationTests {

        @Nested
        @WebMvcTest(GetMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class GetMappingControllerTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on function using GetMapping anbnotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DeleteMappingControllerTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on function using DeleteMapping anbnotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PatchMappingControllerTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on function using PatchMapping anbnotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PostMappingControllerTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on function using PostMapping anbnotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PutMappingControllerTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `endpoint defined on function using PutMapping anbnotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        inner class HttpMethodMappingAnnotationTests {

            @Nested
            @WebMvcTest(MultipleHttpMethodMappingAnnotationsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
            inner class MultipleHttpMethodMappingAnnotationsTest {
                @Autowired
                lateinit var context: ConfigurableApplicationContext

                @Test
                fun `multiple HttpMethodMapping annotations are not supported by spring - most outer annotation wins`() {
                    //given
                    val specification: Set<Endpoint> = setOf(
                            Endpoint("/todos", GET),
                            Endpoint("/todos", OPTIONS),
                            Endpoint("/todos", HEAD)
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
    inner class ConjunctionTests {

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassAndFunctionWithDifferentHttpMethodsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassAndFunctionWithDifferentHttpMethodsTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `No overwrite for RequestMapping defined on class and function for the same path with different http methods - instead both http methods will be supported`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", PATCH),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingDefinedOnClassAndFunctionWithDifferentHttpMethodsAndDifferentPathsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnClassAndFunctionWithDifferentHttpMethodsAndDifferentPathsTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping defined on class and function for the different paths with different http methods will create two endpoints with combined http methods for the nested path`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD),
                        Endpoint("/todos/{id}", GET),
                        Endpoint("/todos/{id}", PUT),
                        Endpoint("/todos/{id}", OPTIONS),
                        Endpoint("/todos/{id}", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingWithPostAndGetMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingWithPostAndGetMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping providing POST defined on class and GetMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingWithPostAndDeleteMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingWithPostAndDeleteMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping providing POST defined on class and DeleteMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingWithPostAndPatchMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingWithPostAndPatchMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping providing POST defined on class and PatchMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingWithTraceAndPostMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingWithTraceAndPostMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping providing TRACE defined on class and PostMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST),
                        Endpoint("/todos", TRACE),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingWithPostAndPutMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingWithPostAndPutMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping providing POST defined on class and PutMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(EmptyRequestMappingAndGetMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class EmptyRequestMappingAndGetMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping without http method defined on class and GetMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(EmptyRequestMappingAndDeleteMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class EmptyRequestMappingAndDeleteMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping without http method defined on class and DeleteMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(EmptyRequestMappingAndPatchMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class EmptyRequestMappingAndPatchMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping without http method defined on class and PatchMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(EmptyRequestMappingAndPostMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class EmptyRequestMappingAndPostMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping without http method defined on class and PostMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(EmptyRequestMappingAndPutMappingInCombinationController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class EmptyRequestMappingAndPutMappingInCombinationTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping without http method defined on class and PutMapping on function`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos", HEAD)
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
        @WebMvcTest(RequestMappingFirstAndGetMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingFirstAndGetMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and GetMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(GetMappingFirstAndRequestMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class GetMappingFirstAndRequestMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and GetMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(RequestMappingFirstAndDeleteMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingFirstAndDeleteMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and DeleteMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(DeleteMappingFirstAndRequestMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DeleteMappingFirstAndRequestMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and DeleteMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(RequestMappingFirstAndPatchMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingFirstAndPatchMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and PatchMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(PatchMappingFirstAndRequestMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PatchMappingFirstAndRequestMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and PatchMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(RequestMappingFirstAndPostMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingFirstAndPostMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and PostMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(PostMappingFirstAndRequestMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PostMappingFirstAndRequestMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and PostMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(RequestMappingFirstAndPutMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class RequestMappingFirstAndPutMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and PutMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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

        @Nested
        @WebMvcTest(PutMappingFirstAndRequestMappingBothOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class PutMappingFirstAndRequestMappingBothOnFunctionTest {
            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `RequestMapping and PutMapping both on function, but behaves like RequestMapping without http methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
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
}