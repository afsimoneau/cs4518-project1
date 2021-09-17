package com.example.cs4518_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

class ClickSave : AppCompatActivity() {
    private lateinit var teamBScore: TextView
    private lateinit var teamAScore: TextView
    private lateinit var teamAText: TextView
    private lateinit var teamBText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_save)
        val savedScoreA = intent.getStringExtra(scoreA)
        val savedScoreB = intent.getStringExtra(scoreB)
        val savedTextA = intent.getStringExtra(teamAName)
        val savedTextB= intent.getStringExtra(teamBName)
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, true)
        }
        setResult(Activity.RESULT_OK, data)

        teamAScore = findViewById<TextView>(R.id.TeamAScore).apply { text= savedScoreA }
        teamBScore = findViewById<TextView>(R.id.TeamBScore).apply { text= savedScoreB }
        teamAText = findViewById<TextView>(R.id.TeamAText).apply { text= savedTextA }
        teamBText = findViewById<TextView>(R.id.TeamBText).apply { text= savedTextB }

    }

}

