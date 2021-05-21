package com.example.quadsolutionstrivia

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val myQuestion = Question()
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun question() {
        myQuestion.category = "hi"
        myQuestion.category = "ello"
        assertEquals("ello", myQuestion.category)
    }

    @Test
    fun isNull() {
        assertEquals(null, myQuestion.correct_answer)
    }
}