package com.example.cs4518_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

class ClickSave : AppCompatActivity() {
    private lateinit var teamBScore: TextView
    private lateinit var teamAScore: TextView
    private lateinit var teamAText: TextView
    private lateinit var teamBText: TextView

    private lateinit var newGameButt: Button
    private lateinit var historyButt: Button
    private lateinit var saveButt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_save)
        val savedScoreA = intent.getStringExtra(scoreA)
        val savedScoreB = intent.getStringExtra(scoreB)
        val savedTextA = intent.getStringExtra(teamAName)
        val savedTextB = intent.getStringExtra(teamBName)
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, true)
        }
        setResult(Activity.RESULT_OK, data)

        teamAScore = findViewById<TextView>(R.id.teamAScore).apply { text = savedScoreA }
        teamBScore = findViewById<TextView>(R.id.teamBScore).apply { text = savedScoreB }
        teamAText = findViewById<TextView>(R.id.teamAText).apply { text = savedTextA }
        teamBText = findViewById<TextView>(R.id.teamBText).apply { text = savedTextB }

        newGameButt = findViewById(R.id.NewGameButt)
        historyButt = findViewById(R.id.btn_gamesHistory)
        saveButt = findViewById(R.id.saveButt)

        newGameButt.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) //idk if this actually works
            Log.d(this::class.java.toString(), "newGameButt")

            intent.putExtra("TARGET_FRAGMENT", "SCORE")

            startActivity(intent)
        }
        historyButt.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) //idk if this actually works
            Log.d(this::class.java.toString(), "historyButt")

            intent.putExtra("TARGET_FRAGMENT", "HISTORY")

            startActivity(intent)
        }

    }

}

