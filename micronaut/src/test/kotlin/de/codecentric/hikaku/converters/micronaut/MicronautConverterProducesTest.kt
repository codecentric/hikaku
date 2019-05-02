package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MicronautConverterProducesTest {

    @Nested
    inner class DeclaredByControllerOnClass {

        @Test
        fun `single media type`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(
                                    "text/plain"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.produces.onclass.onlycontroller.singlemediatype").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `multiple media types`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(
                                    "text/plain",
                                    "application/xml"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.produces.onclass.onlycontroller.multiplemediatypes").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    inner class ProducesOnClassOverridesController {

        @Test
        fun `single media type`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(
                                    "application/xml"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.produces.onclass.producesoverridescontroller.singlemediatype").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `multiple media types`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(
                                    "application/json",
                                    "application/pdf"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.produces.onclass.producesoverridescontroller.multiplemediatypes").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    inner class DeclaredByProducesOnFunction {

        @Test
        fun `single media type`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(
                                    "text/plain"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.produces.onfunction.onlyproduces.singlemediatype").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `multiple media types`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(
                                    "text/plain",
                                    "application/xml"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.produces.onfunction.onlyproduces.multiplemediatypes").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    inner class ProducesOnFunctionOverridesController {

        @Test
        fun `single media type`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(
                                    "application/xml"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.produces.onfunction.producesoverridescontroller.singlemediatype").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `multiple media types`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = GET,
                            produces = setOf(
                                    "application/json",
                                    "application/pdf"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.produces.onfunction.producesoverridescontroller.multiplemediatypes").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Test
    fun `use default media if no produces info has been set`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = GET,
                        produces = setOf(
                                "application/json"
                        )
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.produces.default").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}