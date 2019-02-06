package de.codecentric.hikaku.converter.spring.produces

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

class SpringConverterProducesTest {

    @Nested
    inner class RequestMappingAnnotationTests {

        @Nested
        @WebMvcTest(RequestMappingOneMediaTypeIsInheritedByAllMethodsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsInheritedByAllMethodsTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping annotation is inherited by all methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos/{id}", GET, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", POST, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", PUT, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", PATCH, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", DELETE, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingMultipleMediaTypesAreInheritedByAllMethodsController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreInheritedByAllMethodsTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at class level using RequestMapping annotation are inherited by all methods`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos/{id}", GET, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos/{id}", POST, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos/{id}", HEAD, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos/{id}", PUT, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos/{id}", PATCH, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos/{id}", DELETE, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos/{id}", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingOneMediaTypeIsExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at method level using RequestMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingMultipleMediaTypesAreExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at method level using RequestMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsOverwrittenByDeclarationOnMethodTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by a declaration on method level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos/{id}", GET, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", POST, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", PUT, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", PATCH, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", DELETE, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos/{id}", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnMethodController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreOverwrittenByDeclarationOnMethodTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at class level using RequestMapping is overwritten by a declaration on method level`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE)),
                        Endpoint("/todos", OPTIONS),
                        Endpoint("/todos/{id}", GET, produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)),
                        Endpoint("/todos/{id}", POST, produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)),
                        Endpoint("/todos/{id}", HEAD, produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)),
                        Endpoint("/todos/{id}", PUT, produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)),
                        Endpoint("/todos/{id}", PATCH, produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)),
                        Endpoint("/todos/{id}", DELETE, produces = setOf(APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE)),
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
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingOnMethodDefaultValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class DefinedOnMethodUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at method level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingWithoutProducesInfoAndStringAsReturnValueDefinedOnFunctionController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoProducesInfoAtFunctionLevelAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at class level will fallback to default`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(RequestMappingWithoutProducesInfoAndStringAsReturnValueDefinedOnClassController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoProducesInfoAtClassLevelAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at class level will fallback to default`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", POST, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PUT, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", PATCH, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", DELETE, produces = setOf(TEXT_PLAIN_VALUE)),
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
    inner class GetMappingAnnotationTests {

        @Nested
        @WebMvcTest(GetMappingOneMediaTypeIsExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at method level using GetMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(GetMappingMultipleMediaTypesAreExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at method level using GetMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
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
        inner class DefinedOnMethodUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at method level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(GetMappingWithoutProducesInfoAndStringAsReturnValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoProducesInfoAtFunctionLevelAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at class level will fallback to default`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE)),
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
        @WebMvcTest(DeleteMappingOneMediaTypeIsExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at method level using DeleteMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingMultipleMediaTypesAreExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at method level using DeleteMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
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
        inner class DefinedOnMethodUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at method level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(DeleteMappingWithoutProducesInfoAndStringAsReturnValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoProducesInfoAtFunctionLevelAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at class level will fallback to default`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", DELETE, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE)),
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
        @WebMvcTest(PatchMappingOneMediaTypeIsExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at method level using PatchMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingMultipleMediaTypesAreExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at method level using PatchMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
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
        inner class DefinedOnMethodUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at method level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PatchMappingWithoutProducesInfoAndStringAsReturnValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoProducesInfoAtFunctionLevelAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at class level will fallback to default`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PATCH, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE)),
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
        @WebMvcTest(PostMappingOneMediaTypeIsExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at method level using PostMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingMultipleMediaTypesAreExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at method level using PostMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
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
        inner class DefinedOnMethodUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at method level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PostMappingWithoutProducesInfoAndStringAsReturnValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoProducesInfoAtFunctionLevelAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at class level will fallback to default`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE)),
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
        @WebMvcTest(PutMappingOneMediaTypeIsExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class OneMediaTypeIsExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `media type declared at method level using PutMapping annotation is extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingMultipleMediaTypesAreExractedCorrectlyController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class MultipleMediaTypesAreExractedCorrectlyTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `multiple media types declared at method level using PutMapping annotation are extracted correctly`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE)),
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
        inner class DefinedOnMethodUsingDefaultValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at method level will fallback to default media type`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(APPLICATION_JSON_UTF8_VALUE)),
                        Endpoint("/todos", OPTIONS)
                )

                //when
                val implementation = SpringConverter(context)

                //then
                assertThat(implementation.conversionResult).containsExactlyInAnyOrderElementsOf(specification)
            }
        }

        @Nested
        @WebMvcTest(PutMappingWithoutProducesInfoAndStringAsReturnValueController::class, excludeAutoConfiguration = [ErrorMvcAutoConfiguration::class])
        inner class NoProducesInfoAtFunctionLevelAndStringAsReturnValueTest {

            @Autowired
            lateinit var context: ConfigurableApplicationContext

            @Test
            fun `no media type declared at class level will fallback to default`() {
                //given
                val specification: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT, produces = setOf(TEXT_PLAIN_VALUE)),
                        Endpoint("/todos", HEAD, produces = setOf(TEXT_PLAIN_VALUE)),
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