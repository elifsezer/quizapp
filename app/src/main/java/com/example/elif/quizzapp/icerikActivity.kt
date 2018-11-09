package com.example.elif.quizzapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_icerik.*

class icerikActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icerik)

        b1.setOnClickListener {
            QuizGit()
        }

    }

    fun QuizGit()
    {
        var intent=Intent(applicationContext,QuizActivity::class.java)
        startActivity(intent)
    }
}
