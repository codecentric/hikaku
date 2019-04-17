package de.codecentric.hikaku.converters.micronaut

import de.codecentric.hikaku.converters.EndpointConverterException
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class MicronautConverterPackageDefinitionTest {

    @Test
    fun `invoking converter with empty string leads to EndpointConverterException`() {
        assertFailsWith<EndpointConverterException> {
            MicronautConverter("").conversionResult
        }
    }

    @Test
    fun `invoking converter with blank string leads to EndpointConverterException`() {
        assertFailsWith<EndpointConverterException> {
            MicronautConverter("     ").conversionResult
        }
    }
}