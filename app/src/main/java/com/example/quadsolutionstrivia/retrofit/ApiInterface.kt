package com.example.quadsolutionstrivia.retrofit

import com.example.quadsolutionstrivia.model.Answer
import com.example.quadsolutionstrivia.model.CheckAnswer
import com.example.quadsolutionstrivia.model.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @GET("/questions")
    fun getQuestions(): Call<Result>


    @POST("/checkanswer")
    fun postQuestion(@Body answer: Answer): Call<CheckAnswer>

    companion object {
        var BASE_URL = "http://10.0.2.2:8080"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}