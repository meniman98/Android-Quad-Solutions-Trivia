package com.example.quadsolutionstrivia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.quadsolutionstrivia.databinding.FragmentQuizBinding
import com.example.quadsolutionstrivia.model.Answer
import com.example.quadsolutionstrivia.model.CheckAnswer
import com.example.quadsolutionstrivia.model.Question
import com.example.quadsolutionstrivia.model.Result
import com.example.quadsolutionstrivia.retrofit.ApiInterface
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizFragment : Fragment() {

    private var varBinding: FragmentQuizBinding? = null
    private val binding get() = varBinding!!
    private lateinit var result: Result
    private lateinit var radioGroup: RadioGroup
    private lateinit var question1: String
    private lateinit var question2: String
    private lateinit var question3: String
    private lateinit var question4: String
    private lateinit var questionsArray: ArrayList<String>
    private lateinit var title: String
    private var count: Int = 0
    private val apiInterface = ApiInterface.create().getQuestions()
    private lateinit var rbArray: Array<RadioButton>
    private lateinit var answer: Answer
    private lateinit var radioButton: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewbinding
        varBinding = FragmentQuizBinding.inflate(inflater, container, false)
        result = Result()
        val view = binding.root

        radioGroup = binding.rgQuestions
        rbArray = arrayOf(binding.rb1, binding.rb2, binding.rb3, binding.rb4)

        //perform network call
        CoroutineScope(IO).launch {
            networkCall()
        }

        // show next question
        binding.btnSubmit.setOnClickListener {
            val selectedButton = radioGroup.checkedRadioButtonId
            checkPostAnswer(selectedButton)
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun networkCall() {
        apiInterface.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.body() != null) {
                    Log.i("Quiz", "response works")
                    result = response.body()!!
                    showQuestion()
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.i("Quiz", t.message!!)
            }
        })
    }

    private fun showQuestion() {
        title = result.results?.get(count)?.question.toString()
        binding.tvQuestion.text = title
        questionBinding()
    }

    private fun questionBinding() {
        question1 = result.results?.get(count)?.incorrectAnswers?.get(0).toString()
        question2 = result.results?.get(count)?.incorrectAnswers?.get(1).toString()
        question3 = result.results?.get(count)?.incorrectAnswers?.get(2).toString()
        question4 = result.results?.get(count)?.correctAnswer.toString()
        questionsArray = arrayListOf(question1, question2, question3, question4)
        questionsArray.shuffle()
        questionsArray.forEachIndexed { i, question ->
            rbArray[i].text = question
        }
    }

    private fun checkAnswer(id: Int): Boolean {
        radioButton = view?.findViewById(id)!!
        if (radioButton.text.equals(result.results?.get(count)?.correctAnswer)) {
            return true
        }
        return false
    }

    private fun checkPostAnswer(id: Int) {
        radioButton = view?.findViewById(id)!!
        val radioAnswer: String = radioButton.text.toString()
        val question: Question = result.results!![count]
        answer = Answer(radioAnswer, question)
        val postRequest = ApiInterface.create().postQuestion(answer)

        postRequest.enqueue(object : Callback<CheckAnswer> {
            override fun onFailure(call: Call<CheckAnswer>, t: Throwable) {
                Log.i("Quiz", t.message!!)
            }

            override fun onResponse(call: Call<CheckAnswer>, response: Response<CheckAnswer>) {
                if (response.body()?.result!!) {
                    Log.i("Quiz", response.code().toString())
                    if (count < 4) {
                        count++
                        showQuestion()
                    } else {
                        val it: Button = binding.btnSubmit
                        it.visibility = View.GONE
                        Snackbar.make(it, "You won the quiz!", Snackbar.LENGTH_INDEFINITE)
                            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                            .setAction("Replay") {
                                binding.btnSubmit.visibility = View.VISIBLE
                                count = 0
                                showQuestion()
                            }.show()
                    }

                }


            }

        })


    }
}






