package de.codecentric.hikaku.converters.wadl

import de.codecentric.hikaku.converters.SpecificationParserException
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.xml.sax.SAXParseException
import java.io.File
import java.nio.file.Paths
import kotlin.test.assertFailsWith

class WadlConverterInvalidInputTest {

    @Nested
    inner class PathObjectTests {

        @Test
        fun `empty file returns an empty list`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/empty_file.wadl").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                WadlConverter(file).conversionResult
            }
        }

        @Test
        fun `file consisting solely of whitespaces returns an empty list`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/whitespaces_only_file.wadl").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                WadlConverter(file).conversionResult
            }
        }

        @Test
        fun `file containing syntax error`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/syntax_error.wadl").toURI())
            val converter = WadlConverter(file)

            //when
            assertFailsWith<SpecificationParserException> {
                converter.conversionResult
            }
        }
    }

    @Nested
    inner class FileObjectTests {

        @Test
        fun `empty file returns an empty list`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/empty_file.wadl").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                WadlConverter(file).conversionResult
            }
        }

        @Test
        fun `file consisting solely of whitespaces returns an empty list`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/whitespaces_only_file.wadl").toURI())

            //when
            assertFailsWith<SpecificationParserException> {
                WadlConverter(file).conversionResult
            }
        }

        @Test
        fun `file containing syntax error`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/syntax_error.wadl").toURI())
            val converter = WadlConverter(file)

            //when
            assertFailsWith<SpecificationParserException> {
                converter.conversionResult
            }
        }
    }
}