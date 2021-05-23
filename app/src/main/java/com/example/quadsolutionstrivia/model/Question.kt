package com.example.quadsolutionstrivia.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class Question {
    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("difficulty")
    @Expose
    var difficulty: String? = null

    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("correct_answer")
    @Expose
    var correctAnswer: String? = null

    @SerializedName("incorrect_answers")
    @Expose
    var incorrectAnswers: List<String>? = null
}