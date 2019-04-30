package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod
import de.codecentric.hikaku.endpoints.HttpMethod.GET
import de.codecentric.hikaku.endpoints.HttpMethod.POST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MicronautConverterConsumesTest {

    @Nested
    inner class DeclaredByControllerOnClass {

        @Test
        fun `single media type`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = POST,
                            consumes = setOf(
                                "text/plain"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.consumes.onclass.onlycontroller.singlemediatype").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `multiple media types`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = POST,
                            consumes = setOf(
                                    "text/plain",
                                    "application/xml"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.consumes.onclass.onlycontroller.multiplemediatypes").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    inner class ConsumesOnClassOverridesController {

        @Test
        fun `single media type`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = POST,
                            consumes = setOf(
                                    "application/xml"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.consumes.onclass.consumesoverridescontroller.singlemediatype").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `multiple media types`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = POST,
                            consumes = setOf(
                                    "application/json",
                                    "application/pdf"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.consumes.onclass.consumesoverridescontroller.multiplemediatypes").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    inner class DeclaredByConsumesOnFunction {

        @Test
        fun `single media type`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = POST,
                            consumes = setOf(
                                    "text/plain"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.consumes.onfunction.onlyconsumes.singlemediatype").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `multiple media types`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = POST,
                            consumes = setOf(
                                    "text/plain",
                                    "application/xml"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.consumes.onfunction.onlyconsumes.multiplemediatypes").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    inner class ConsumesOnFunctionOverridesController {

        @Test
        fun `single media type`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = POST,
                            consumes = setOf(
                                    "application/xml"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.consumes.onfunction.consumesoverridescontroller.singlemediatype").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `multiple media types`() {
            //given
            val specification = setOf(
                    Endpoint(
                            path = "/todos",
                            httpMethod = POST,
                            consumes = setOf(
                                    "application/json",
                                    "application/pdf"
                            )
                    )
            )

            //when
            val result = MicronautConverter("test.micronaut.consumes.onfunction.consumesoverridescontroller.multiplemediatypes").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Test
    fun `use default media if no consume info has been set`() {
        //given
        val specification = setOf(
                Endpoint(
                        path = "/todos",
                        httpMethod = POST,
                        consumes = setOf(
                                "application/json"
                        )
                )
        )

        //when
        val result = MicronautConverter("test.micronaut.consumes.default").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}