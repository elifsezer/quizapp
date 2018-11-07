package com.example.elif.quizzapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       button_start_quiz.setOnClickListener {
           starQuiz()
       }

    }
    private fun starQuiz() {
        var intent = Intent(applicationContext, QuizActivity::class.java)
        startActivity(intent)

    }
}
