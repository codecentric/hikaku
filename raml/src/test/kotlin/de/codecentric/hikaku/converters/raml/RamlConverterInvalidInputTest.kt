package de.codecentric.hikaku.converters.raml

import de.codecentric.hikaku.converters.SpecificationParserException
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Paths
import kotlin.test.assertFailsWith

class RamlConverterInvalidInputTest {

    @Nested
    inner class FileObjectTests {

        @Test
        fun `empty file returns an empty list`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/empty_file.raml").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                RamlConverter(file).conversionResult
            }
        }

        @Test
        fun `file consisting solely of whitespaces returns an empty list`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/whitespaces_only_file.raml").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                RamlConverter(file).conversionResult
            }
        }

        @Test
        fun `invalid RAML version`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/invalid_raml_version.raml").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                RamlConverter(file).conversionResult
            }
        }

        @Test
        fun `file containing syntax error throws SpecificationParserException`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/syntax_error.raml").toURI())
            val converter = RamlConverter(file)

            //when
            assertFailsWith<SpecificationParserException> {
                converter.conversionResult
            }
        }
    }

    @Nested
    inner class PathObjectTests {

        @Test
        fun `empty file returns an empty list`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/empty_file.raml").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                RamlConverter(file).conversionResult
            }
        }

        @Test
        fun `file consisting solely of whitespaces returns an empty list`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/whitespaces_only_file.raml").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                RamlConverter(file).conversionResult
            }
        }

        @Test
        fun `invalid RAML version`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/invalid_raml_version.raml").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                RamlConverter(file).conversionResult
            }
        }

        @Test
        fun `file containing syntax error`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/syntax_error.raml").toURI())
            val converter = RamlConverter(file)

            //when
            assertFailsWith<SpecificationParserException> {
                converter.conversionResult
            }
        }
    }
}