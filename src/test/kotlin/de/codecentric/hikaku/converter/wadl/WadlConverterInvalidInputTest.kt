package de.codecentric.hikaku.converter.wadl

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.xml.sax.SAXParseException
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertFailsWith

class WadlConverterInvalidInputTest {

    @Nested
    inner class EmptyFileTest {

        @Test
        fun `empty string as specification throws an exception`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("wadl/empty_file.yaml").toURI())

            //when
            assertFailsWith<IllegalArgumentException> {
                de.codecentric.hikaku.converter.wadl.WadlConverter(file)
            }
        }

        @Test
        fun `specification string consisting solely of whitespaces throws an exception`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("wadl/whitespace_only_file.yaml").toURI())

            //when
            assertFailsWith<IllegalArgumentException> {
                de.codecentric.hikaku.converter.wadl.WadlConverter(file)
            }
        }
    }

    @Nested
    inner class NonExistingFileTest {

        @Test
        fun `non-existing WADL file provided as File throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                WadlConverter(File("test-file-which-does-not-exist.wadl"))
            }
        }

        @Test
        fun `non-existing WADL file provided as Path throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                WadlConverter(Paths.get("test-file-which-does-not-exist.wadl"))
            }
        }
    }

    @Nested
    inner class NotRegularFileTest {

        @Test
        fun `Path which is a directory`() {
            assertFailsWith<IllegalArgumentException> {
                WadlConverter(Files.createTempDirectory("tmp"))
            }
        }

        @Test
        fun `File which is a directory`() {
            assertFailsWith<IllegalArgumentException> {
                WadlConverter(createTempDir())
            }
        }
    }

    @Nested
    inner class InvalidFileExtensionTest {

        @Test
        fun `existing file with invalid file extension provided as File throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                WadlConverter(File("./README.md"))
            }
        }


        @Test
        fun `existing file with invalid file extension provided as Path throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                WadlConverter(Paths.get("./README.md"))
            }
        }
    }

    @Nested
    inner class SyntaxErrorTest {

        @Test
        fun `WADL file containing syntax error throws SAXParseException`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("wadl/syntax_error.wadl").toURI())
            val converter = WadlConverter(file)

            //when
            assertFailsWith<SAXParseException> {
                converter.conversionResult
            }
        }
    }
}