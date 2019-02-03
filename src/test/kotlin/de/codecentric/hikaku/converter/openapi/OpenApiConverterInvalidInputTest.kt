import de.codecentric.hikaku.converter.openapi.OpenApiConverter
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertFailsWith

class OpenApiConverterInvalidInputTest {

    @Nested
    inner class EmptyFileTest {

        @Test
        fun `empty string as specification throws an exception`() {
            //given
            val file = Paths.get(OpenApiConverterInvalidInputTest::class.java.classLoader.getResource("openapi/empty_file.yaml").toURI())

            //when
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(file)
            }
        }

        @Test
        fun `specification string consisting solely of whitespaces throws an exception`() {
            //given
            val file = Paths.get(OpenApiConverterInvalidInputTest::class.java.classLoader.getResource("openapi/whitespace_only_file.yaml").toURI())

            //when
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(file)
            }
        }
    }

    @Nested
    inner class NonExistingFileTest {

        @Test
        fun `non-existing json file provided as File throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(File("test-file-which-does-not-exist.json"))
            }
        }

        @Test
        fun `non-existing yaml file provided as File throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(File("test-file-which-does-not-exist.yaml"))
            }
        }

        @Test
        fun `non-existing yml file provided as File throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(File("test-file-which-does-not-exist.yml"))
            }
        }

        @Test
        fun `non-existing json file provided as Path throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(Paths.get("test-file-which-does-not-exist.json"))
            }
        }

        @Test
        fun `non-existing yaml file provided as Path throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(Paths.get("test-file-which-does-not-exist.yaml"))
            }
        }

        @Test
        fun `non-existing yml file provided as Path throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(Paths.get("test-file-which-does-not-exist.yml"))
            }
        }
    }

    @Nested
    inner class InvalidFileExtensionTest {

        @Test
        fun `existing file with invalid file extension provided as File throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(File("./README.md"))
            }
        }


        @Test
        fun `existing file with invalid file extension provided as Path throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(Paths.get("./README.md"))
            }
        }
    }

    @Nested
    inner class NotRegularFileTest {

        @Test
        fun `Path which is a directory throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(Files.createTempDirectory("tmp"))
            }
        }

        @Test
        fun `File which is a directory throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(createTempDir())
            }
        }
    }

    @Nested
    inner class SyntaxErrorTest {

        @Test
        fun `OpenAPI yaml file containing syntax error throws IllegalStateException, because OpenAPI object will be null (The parser is extremely fault tolerant)`() {
            //given
            val file = Paths.get(OpenApiConverterInvalidInputTest::class.java.classLoader.getResource("openapi/syntax_error.yaml").toURI())
            val converter = OpenApiConverter(file)

            //when
            assertFailsWith<IllegalStateException> {
                converter.conversionResult
            }
        }
    }
}