package de.codecentric.hikaku.extensions

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.assertFailsWith

class FileExtensionsTest {

    @Nested
    inner class CheckValidityTests {

        @Test
        fun `non-existing file throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                File("test-file-which-does-not-exist.spec").checkFileValidity()
            }
        }

        @Test
        fun `directory in validity check throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                createTempDirectory().checkFileValidity()
            }
        }

        @Test
        fun `existing file with invalid file extension throws an exception`() {
            assertFailsWith<IllegalArgumentException> {
                File(this::class.java.classLoader.getResource("test_file.txt").toURI()).checkFileValidity(".css")
            }
        }

        @Test
        fun `file is valid without extension check`() {
            //given
            val file = File(this::class.java.classLoader.getResource("test_file.txt").toURI())

            //when
            file.checkFileValidity()

            //then
            //no exception is thrown
        }

        @Test
        fun `file is valid with extension check`() {
            //given
            val file = File(this::class.java.classLoader.getResource("test_file.txt").toURI())

            //when
            file.checkFileValidity(".txt")

            //then
            //no exception is thrown
        }
    }
}