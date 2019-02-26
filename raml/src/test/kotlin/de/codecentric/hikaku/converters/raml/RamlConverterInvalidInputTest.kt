package de.codecentric.hikaku.converters.raml

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files.createTempDirectory
import java.nio.file.Paths
import kotlin.test.assertFailsWith

class RamlConverterInvalidInputTest {

    @Nested
    inner class FileObjectTests {

        @Nested
        inner class EmptyFileTest {

            @Test
            fun `empty file returns an empty list`() {
                //given
                val file = File(this::class.java.classLoader.getResource("invalid_input/empty_file.raml").toURI())

                //when
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(file).conversionResult
                }
            }

            @Test
            fun `file consisting solely of whitespaces returns an empty list`() {
                //given
                val file = File(this::class.java.classLoader.getResource("invalid_input/whitespace_only_file.raml").toURI())

                //when
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(file).conversionResult
                }
            }

            @Test
            fun `invalid RAML version`() {
                //given
                val file = File(this::class.java.classLoader.getResource("invalid_input/invalid_raml_version.raml").toURI())

                //when
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(file).conversionResult
                }
            }
        }

        @Nested
        inner class NonExistingFileTest {

            @Test
            fun `non-existing raml file throws an exception`() {
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(File("test-file-which-does-not-exist.raml"))
                }
            }
        }

        @Nested
        inner class InvalidFileExtensionTest {

            @Test
            fun `existing file with invalid file extension throws an exception`() {
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(File(this::class.java.classLoader.getResource("invalid_input/different_extension.css").toURI()))
                }
            }
        }

        @Nested
        inner class NotRegularFileTest {

            @Test
            fun `File which is a directory throws an exception`() {
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(createTempDir())
                }
            }
        }

        @Nested
        inner class SyntaxErrorTest {

            @Test
            fun `raml file containing syntax error throws IllegalArgumentException`() {
                //given
                val file = File(this::class.java.classLoader.getResource("invalid_input/syntax_error.raml").toURI())
                val converter = RamlConverter(file)

                //when
                assertFailsWith<IllegalArgumentException> {
                    converter.conversionResult
                }
            }
        }
    }

    @Nested
    inner class PathObjectTests {

        @Nested
        inner class EmptyFileTest {

            @Test
            fun `empty file returns an empty list`() {
                //given
                val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/empty_file.raml").toURI())

                //when
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(file).conversionResult
                }
            }

            @Test
            fun `file consisting solely of whitespaces returns an empty list`() {
                //given
                val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/whitespace_only_file.raml").toURI())

                //when
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(file).conversionResult
                }
            }

            @Test
            fun `invalid RAML version`() {
                //given
                val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/invalid_raml_version.raml").toURI())

                //when
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(file).conversionResult
                }
            }
        }

        @Nested
        inner class NonExistingFileTest {

            @Test
            fun `non-existing raml file throws an exception`() {
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(Paths.get("test-file-which-does-not-exist.raml"))
                }
            }
        }

        @Nested
        inner class InvalidFileExtensionTest {

            @Test
            fun `existing file with invalid file extension throws an exception`() {
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(Paths.get(this::class.java.classLoader.getResource("invalid_input/different_extension.css").toURI()))
                }
            }
        }

        @Nested
        inner class NotRegularFileTest {

            @Test
            fun `Path which is a directory throws an exception`() {
                assertFailsWith<IllegalArgumentException> {
                    RamlConverter(createTempDirectory("tmp"))
                }
            }
        }

        @Nested
        inner class SyntaxErrorTest {

            @Test
            fun `raml file containing syntax error throws IllegalArgumentException`() {
                //given
                val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/syntax_error.raml").toURI())
                val converter = RamlConverter(file)

                //when
                assertFailsWith<IllegalArgumentException> {
                    converter.conversionResult
                }
            }
        }
    }
}