package de.codecentric.hikaku.extensions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.String as JavaString

class ClassExtensionsTest {

    @Nested
    inner class IsUnitTests {

        @Test
        fun `returns true for kotlin type Unit`() {
            // given
            val obj = Unit::class

            // when
            val result = obj.isUnit()

            // then
            assertThat(result).isTrue()
        }

        @Test
        fun `returns true for java type Void`() {
            // given
            val obj = Void::class

            // when
            val result = obj.isUnit()

            // then
            assertThat(result).isTrue()
        }

        @Test
        fun `returns false for any other type`() {
            // given
            val obj = Int::class

            // when
            val result = obj.isUnit()

            // then
            assertThat(result).isFalse()
        }
    }

    @Nested
    inner class IsStringTests {

        @Test
        fun `returns true for kotlin type String`() {
            // given
            val obj = String::class

            // when
            val result = obj.isString()

            // then
            assertThat(result).isTrue()
        }

        @Test
        fun `returns true for java type String`() {
            // given
            val obj = JavaString::class

            // when
            val result = obj.isString()

            // then
            assertThat(result).isTrue()
        }

        @Test
        fun `returns false for any other type`() {
            // given
            val obj = Int::class

            // when
            val result = obj.isString()

            // then
            assertThat(result).isFalse()
        }
    }
}
