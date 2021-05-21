package com.example.quadsolutionstrivia

import com.example.quadsolutionstrivia.model.CheckAnswer
import com.example.quadsolutionstrivia.model.CheckAnswerRequest
import com.example.quadsolutionstrivia.model.Question
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

    @Test
    fun dataClass() {
        val checkAnswer = CheckAnswer(true)
        assertEquals(true, checkAnswer.result)
    }

    @Test
    fun answerRequest() {
        val answerRequest = CheckAnswerRequest()
        answerRequest.answer = "ello dere"
        assertEquals(null, answerRequest.question)
        assertEquals("ello dere", answerRequest.answer)
    }

}