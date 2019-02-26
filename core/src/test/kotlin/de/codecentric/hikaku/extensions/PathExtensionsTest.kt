package de.codecentric.hikaku.extensions

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.nio.file.Files.createTempDirectory
import java.nio.file.Paths
import kotlin.test.assertFailsWith

class PathExtensionsTest {

    @Nested
    inner class CheckValidityTests {

        @Test
        fun `non-existing file throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                Paths.get("test-file-which-does-not-exist.spec").checkFileValidity()
            }
        }

        @Test
        fun `directory in validity check throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                createTempDirectory("tmp").checkFileValidity()
            }
        }

        @Test
        fun `existing file with invalid file extension throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                Paths.get(this::class.java.classLoader.getResource("test_file.txt").toURI()).checkFileValidity(".css")
            }
        }

        @Test
        fun `file is valid without extension check`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("test_file.txt").toURI())

            //when
            file.checkFileValidity()

            //then
            //no exception is thrown
        }

        @Test
        fun `file is valid with extension check`() {
            //given
            val file = Paths.get(this::class.java.classLoader.getResource("test_file.txt").toURI())

            //when
            file.checkFileValidity(".txt")

            //then
            //no exception is thrown
        }
    }
}