package com.example.elif.quizzapp

import android.content.Context
import android.content.Intent
import android.media.MicrophoneInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Message
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*
import java.sql.Time
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private var isCancelled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_start_quiz.setOnClickListener {
            starQuiz()
        }
    }

    private fun starQuiz() {
        var intent = Intent(applicationContext, icerikActivity::class.java)
        startActivity(intent)
    }
}



