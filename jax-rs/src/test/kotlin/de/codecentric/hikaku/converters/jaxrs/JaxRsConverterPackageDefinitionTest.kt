package de.codecentric.hikaku.converters.jaxrs

import de.codecentric.hikaku.converters.EndpointConverterException
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class JaxRsConverterPackageDefinitionTest {

    @Test
    fun `invoking converter with empty string leads to EndpointConverterException`() {
        assertFailsWith<EndpointConverterException> {
            JaxRsConverter("").conversionResult
        }
    }

    @Test
    fun `invoking converter with blank string leads to EndpointConverterException`() {
        assertFailsWith<EndpointConverterException> {
            JaxRsConverter("     ").conversionResult
        }
    }
}