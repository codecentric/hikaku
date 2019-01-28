package de.codecentric.hikaku.matcher

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PreCheckListSizeMatchResultTest {

    @Test
    fun `A pre-check that doesn't match`() {
        //given
        val preCheckMatchResult = PreCheckListSizeMatchResult(
                "Number of endpoints",
                3,
                4
        )

        //when
        val matches = preCheckMatchResult.matches()

        //then
        assertThat(matches).isFalse()
    }

    @Test
    fun `A pre-check that matches`() {
        //given
        val preCheckMatchResult = PreCheckListSizeMatchResult(
                "Number of query parameters",
                1,
                1
        )

        //when
        val matches = preCheckMatchResult.matches()

        //then
        assertThat(matches).isTrue()
    }
}