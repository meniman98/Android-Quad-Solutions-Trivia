package com.example.quadsolutionstrivia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quadsolutionstrivia.model.Result
import com.example.quadsolutionstrivia.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val apiInterface = ApiInterface.create().getQuestions()

        apiInterface.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.body() != null) {
                    Log.i("Quiz", "response works")
                }

            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.i("Quiz", t.message!!)
            }
        })
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)


    }
}