package com.fake.a2prep

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer

class QuizScreen : AppCompatActivity() {

    val QuestionArray = arrayOf("val x: Int = null", "var count = 1\ncount++", "val name = \"User\"", "val list = arrayOfNulls<Int>(1, 2, 3)")
    val Answers = arrayOf(false, true, true, false)
    var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvCodeSnippet = findViewById<TextView>(R.id.tvCodeSnippet)
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)
        val btnValid = findViewById<Button>(R.id.btnValid)
        val btnError = findViewById<Button>(R.id.btnError)
        val btnNext = findViewById<Button>(R.id.btnNext)

        for(x in 0..3){
            tvCodeSnippet.text = QuestionArray[x]

            btnValid.setOnClickListener {
                for (x in 0..3){
                    if (Answers[x] == true){
                        tvFeedback.text = "Correct"
                        score++
                    }
                    else{
                        tvFeedback.text = "Incorrect"
                    }
                }

                btnNext.visibility = Button.VISIBLE
                btnError.isEnabled = false
                btnValid.isEnabled = false
            }

            btnError.setOnClickListener {
                for (x in 0..3){
                    if (Answers[x] == false){
                        tvFeedback.text = "Correct"
                        score++
                    }
                    else{
                        tvFeedback.text = "Incorrect"
                    }
                }

                btnNext.visibility = Button.VISIBLE
                btnError.isEnabled = false
                btnValid.isEnabled = false
            }

            btnNext.setOnClickListener {
                if(x == 3){
                    Handler(Looper.getMainLooper()).postDelayed({
                        btnNext.visibility = Button.INVISIBLE
                        val intent = Intent(this, ScoreScreen::class.java)
                        intent.putExtra("SCORE", score/2)
                        intent.putExtra("ANSWERS", Answers.toBooleanArray())
                        intent.putExtra("Questions", QuestionArray)
                        startActivity(intent)
                    }, 1500)
                }
                else{
                    tvCodeSnippet.text = QuestionArray[x]
                    tvFeedback.text = ""
                    btnNext.visibility = Button.INVISIBLE
                    btnError.isEnabled = true
                    btnValid.isEnabled = true
                    Log.d("MSG", "User answered question number ${x}")
                }
        }
        }
    }
}