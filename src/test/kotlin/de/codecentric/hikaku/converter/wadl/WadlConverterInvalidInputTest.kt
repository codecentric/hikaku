package de.codecentric.hikaku.converter.wadl

import org.junit.jupiter.api.Test
import org.xml.sax.SAXParseException
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertFailsWith

class WadlConverterInvalidInputTest {

    @Test
    fun `empty string throws an exception`() {
        assertFailsWith<IllegalArgumentException> {
            WadlConverter("")
        }
    }

    @Test
    fun `string consisting solely of whitespaces throws an exception`() {
        assertFailsWith<IllegalArgumentException> {
            WadlConverter("      ")
        }
    }

    @Test
    fun `non-existing WADL file provided as File throws an exception`() {
        assertFailsWith<IllegalArgumentException> {
            WadlConverter(File("test-file-which-does-not-exist.wadl"))
        }
    }

    @Test
    fun `existing file with invalid file extension provided as File throws an exception`() {
        assertFailsWith<IllegalArgumentException> {
            WadlConverter(File("./README.md"))
        }
    }

    @Test
    fun `non-existing WADL file provided as Path throws an exception`() {
        assertFailsWith<IllegalArgumentException> {
            WadlConverter(Paths.get("test-file-which-does-not-exist.wadl"))
        }
    }

    @Test
    fun `existing file with invalid file extension provided as Path throws an exception`() {
        assertFailsWith<IllegalArgumentException> {
            WadlConverter(Paths.get("./README.md"))
        }
    }

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

    @Test
    fun `WADL file containing syntax error throws SAXParseException`() {
        //given
        val file = Paths.get(WadlConverterInvalidInputTest::class.java.classLoader.getResource("wadl/syntax_error.wadl").toURI())
        val converter = WadlConverter(file)

        //when
        assertFailsWith<SAXParseException> {
            converter.conversionResult
        }
    }
}