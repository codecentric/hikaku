package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MicronautConverterPathTest {

    @Nested
    inner class PathOnlyOnControllerTests {

        @Test
        fun `controller annotation with DELETE annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todos", DELETE)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.onlycontrollerannotation.delete").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with GET annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todos", GET)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.onlycontrollerannotation.get").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with HEAD annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todos", HEAD)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.onlycontrollerannotation.head").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with OPTIONS annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todos", OPTIONS)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.onlycontrollerannotation.options").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with PATCH annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todos", PATCH)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.onlycontrollerannotation.patch").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with POST annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todos", POST)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.onlycontrollerannotation.post").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with PUT annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todos", PUT)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.onlycontrollerannotation.put").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Nested
    inner class PathInBothControllerAndHttpMethodAnnotationTests {

        @Test
        fun `controller annotation with DELETE annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todo/list", DELETE)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.combinedcontrollerandhttpmethodannotation.delete").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with GET annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todo/list", GET)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.combinedcontrollerandhttpmethodannotation.get").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with HEAD annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todo/list", HEAD)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.combinedcontrollerandhttpmethodannotation.head").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with OPTIONS annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todo/list", OPTIONS)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.combinedcontrollerandhttpmethodannotation.options").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with PATCH annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todo/list", PATCH)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.combinedcontrollerandhttpmethodannotation.patch").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with POST annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todo/list", POST)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.combinedcontrollerandhttpmethodannotation.post").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }

        @Test
        fun `controller annotation with PUT annotation`() {
            //given
            val specification = setOf(
                    Endpoint("/todo/list", PUT)
            )

            //when
            val result = MicronautConverter("test.micronaut.path.combinedcontrollerandhttpmethodannotation.put").conversionResult

            //then
            assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
        }
    }

    @Test
    fun `method does not provide any http method annotation`() {
        //when
        val result = MicronautConverter("test.micronaut.path.nohttpmethodannotation").conversionResult

        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `inner path segment without leading slash`() {
        //given
        val specification = setOf(
                Endpoint("/todo/list", GET)
        )

        //when
        val result = MicronautConverter("test.micronaut.path.innerpathsegmentwithoutleadingslash").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }

    @Test
    fun `first path segment without leading slash`() {
        //given
        val specification = setOf(
                Endpoint("/todo/list", GET)
        )

        //when
        val result = MicronautConverter("test.micronaut.path.firstpathsegmentwithoutleadingslash").conversionResult

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(specification)
    }
}