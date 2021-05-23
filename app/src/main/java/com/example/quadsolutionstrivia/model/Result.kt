package com.example.quadsolutionstrivia.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



public class Result {
    @SerializedName("results")
    @Expose
    var results: List<Question>? = null
}