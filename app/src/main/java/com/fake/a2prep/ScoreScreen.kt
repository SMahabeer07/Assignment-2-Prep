package com.fake.a2prep

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvScoreDisplay = findViewById<TextView>(R.id.tvScoreDisplay)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnRestart = findViewById<Button>(R.id.btnRestart)
        val btnClose = findViewById<Button>(R.id.btnClose)
        val tvAnswers = findViewById<TextView>(R.id.tvAnswers)

        val score = intent.getIntExtra("SCORE", 0)/2
        val answers = intent.getBooleanArrayExtra("ANSWERS")
        val questions = intent.getStringArrayExtra("Questions")

        tvScoreDisplay.text = "${score}/4"

        btnRestart.setOnClickListener {
            val intent = Intent(this, QuizScreen::class.java)
            startActivity(intent)
            finish()
        }

        btnReview.setOnClickListener {
            Log.d("ANSWERS", answers.toString())
            Log.d("QUESTIONS", questions.toString())
            tvAnswers.text = ""
            if (answers != null) {
                for(x in 0..3){
                    tvAnswers.text = buildString {
                        append(tvAnswers.text.toString())
                        append((questions?.get(x) + ": " + answers[x]))
                        append("\n")
                    }
                }
            }
        }

        btnClose.setOnClickListener {
            finishAffinity()
        }

    }
}