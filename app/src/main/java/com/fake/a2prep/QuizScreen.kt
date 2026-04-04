package com.fake.a2prep

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    var counter = 0
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


        tvCodeSnippet.text = QuestionArray[counter]
        var lcv = 0

        btnValid.setOnClickListener {
            for (x in Answers){
                if (Answers[counter] == true ){
                    tvFeedback.text = "Correct"
                    score++
                }
                else{
                    tvFeedback.text = "Incorrect"
                }
                lcv++
            }

            btnNext.visibility = Button.VISIBLE
            btnError.isEnabled = false
            btnValid.isEnabled = false
        }

        btnError.setOnClickListener {
            for (x in Answers){
                if (Answers[counter] == false){
                    tvFeedback.text = "Correct"
                    score++
                }
                else{
                    tvFeedback.text = "Incorrect"
                }

                lcv++
            }

            btnNext.visibility = Button.VISIBLE
            btnError.isEnabled = false
            btnValid.isEnabled = false
        }

        btnNext.setOnClickListener {
            if(counter == 3){
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
                counter++
                tvCodeSnippet.text = QuestionArray[counter]
                tvFeedback.text = ""
                btnNext.visibility = Button.INVISIBLE
                btnError.isEnabled = true
                btnValid.isEnabled = true
            }
        }
    }
}