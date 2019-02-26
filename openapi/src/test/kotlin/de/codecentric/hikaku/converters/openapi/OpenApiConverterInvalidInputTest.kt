import de.codecentric.hikaku.converters.openapi.OpenApiConverter
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertFailsWith

class OpenApiConverterInvalidInputTest {

    @Nested
    inner class PathObjectTests {

        @Test
        fun `empty file returns an empty list`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/empty_file.yaml").toURI())

            //when
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(file).conversionResult
            }
        }

        @Test
        fun `file consisting solely of whitespaces returns an empty list`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/whitespaces_only_file.yaml").toURI())

            //when
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(file).conversionResult
            }
        }

        @Test
        fun `OpenAPI yaml file containing syntax error throws IllegalStateException, because OpenAPI object will be null (The parser is extremely fault tolerant)`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("invalid_input/syntax_error.yaml").toURI())
            val converter = OpenApiConverter(file)

            //when
            assertFailsWith<IllegalStateException> {
                converter.conversionResult
            }
        }
    }

    @Nested
    inner class FileObjectTests {

        @Test
        fun `empty file returns an empty list`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/empty_file.yaml").toURI())

            //when
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(file).conversionResult
            }
        }

        @Test
        fun `file consisting solely of whitespaces returns an empty list`() {
            //given
                val file = File(this::class.java.classLoader.getResource("invalid_input/whitespaces_only_file.yaml").toURI())

            //when
            assertFailsWith<IllegalArgumentException> {
                OpenApiConverter(file).conversionResult
            }
        }

        @Test
        fun `OpenAPI yaml file containing syntax error throws IllegalStateException, because OpenAPI object will be null (The parser is extremely fault tolerant)`() {
            //given
            val file = File(this::class.java.classLoader.getResource("invalid_input/syntax_error.yaml").toURI())
            val converter = OpenApiConverter(file)

            //when
            assertFailsWith<IllegalStateException> {
                converter.conversionResult
            }
        }
    }
}