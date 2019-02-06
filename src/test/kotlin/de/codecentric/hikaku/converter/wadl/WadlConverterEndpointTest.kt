package de.codecentric.hikaku.converter.wadl

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class WadlConverterEndpointTest {
    
    @Test
    fun `extract two different paths`() {
        //given
        val file = Paths.get(WadlConverterEndpointTest::class.java.classLoader.getResource("wadl/endpoints/endpoints_two_different_paths.wadl").toURI())
        val implementation: Set<Endpoint> = setOf(
            Endpoint("/todos", GET),
            Endpoint("/tags", GET)
        )

        //when
        val specification = WadlConverter(file).conversionResult
        
        //then
        assertThat(specification).containsAll(implementation)
    }

    @Test
    fun `extract two nested paths`() {
        //given
        val file = Paths.get(WadlConverterEndpointTest::class.java.classLoader.getResource("wadl/endpoints/endpoints_two_nested_paths.wadl").toURI())
        val implementation: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos/{id}", GET)
        )

        //when
        val specification = WadlConverter(file).conversionResult

        //then
        assertThat(specification).containsAll(implementation)
    }

    @Test
    fun `extract all http methods`() {
        //given
        val file = Paths.get(WadlConverterEndpointTest::class.java.classLoader.getResource("wadl/endpoints/endpoints.wadl").toURI())
        val implementation: Set<Endpoint> = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", POST),
                Endpoint("/todos", PUT),
                Endpoint("/todos", PATCH),
                Endpoint("/todos", DELETE),
                Endpoint("/todos", HEAD),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", TRACE),
                Endpoint("/tags", GET),
                Endpoint("/tags", POST),
                Endpoint("/tags", DELETE),
                Endpoint("/tags", HEAD),
                Endpoint("/tags", OPTIONS)
        )

        //when
        val specification = WadlConverter(file).conversionResult

        //then
        assertThat(specification).containsAll(implementation)
    }
}