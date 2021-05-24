package com.example.quadsolutionstrivia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quadsolutionstrivia.databinding.FragmentQuizBinding
import com.example.quadsolutionstrivia.model.Question
import com.example.quadsolutionstrivia.model.Result
import com.example.quadsolutionstrivia.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizFragment : Fragment() {

    private var varBinding: FragmentQuizBinding? = null
    private val binding get() = varBinding!!
    private lateinit var result: Result
    private lateinit var question1: String
    private lateinit var question2: String
    private lateinit var question3: String
    private lateinit var question4: String
    private lateinit var title: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        varBinding = FragmentQuizBinding.inflate(inflater, container, false)
        result = Result()
        val view = binding.root


        val apiInterface = ApiInterface.create().getQuestions()

        apiInterface.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.body() != null) {
                    Log.i("Quiz", "response works")
                    title = response.body()!!.results?.get(0)?.question.toString()
                    binding.tvQuestion.text = title
                }

            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.i("Quiz", t.message!!)
            }
        })
        // Inflate the layout for this fragment
        return view


    }
}