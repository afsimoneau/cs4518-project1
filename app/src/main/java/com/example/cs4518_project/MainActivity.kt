package com.example.cs4518_project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import android.content.Intent
import android.view.View
import android.widget.EditText


//view
val scoreA = "scoreA.MESSAGE"
val scoreB = "scoreB.MESSAGE"
val teamAName = "TeamAName.MESSAGE"
val teamBName = "TeamBName.MESSAGE"
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TeamViewModel

    private lateinit var button3a: Button
    private lateinit var button3b: Button
    private lateinit var button2a: Button
    private lateinit var button2b: Button
    private lateinit var freeThrowA: Button
    private lateinit var freeThrowB: Button
    private lateinit var resetButt: Button
    private lateinit var teamBScore: TextView
    private lateinit var teamAScore: TextView
    private lateinit var teamAText: TextView
    private lateinit var teamBText: TextView
    private lateinit var save: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO: save team scores in a ViewModel
        //TODO: clean up layouts/views in activity_main and activity_landscape
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation

        if (orientation == 1) {
            setContentView(R.layout.activity_main)
        } else {
            setContentView(R.layout.activity_landscape)
        }

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        val view = MainActivity()
        val controller = TeamController(viewModel, view)

        if (savedInstanceState != null) {
            viewModel.TeamAName = savedInstanceState.getString("teamA").toString()
            viewModel.TeamBName = savedInstanceState.getString("teamB").toString()
        } else {
            viewModel.TeamAName = "Team A"
            viewModel.TeamBName = "Team B"
        }

        button3a = findViewById(R.id.button3a)
        button3b = findViewById(R.id.button3b)
        button2a = findViewById(R.id.button2a)
        button2b = findViewById(R.id.button2b)
        freeThrowB = findViewById(R.id.FreeThrowB)
        freeThrowA = findViewById(R.id.FreeThrowA)
        resetButt = findViewById(R.id.ResetButt)

        teamAScore = findViewById<TextView>(R.id.TeamAScore).apply {
            text = viewModel.ScoreA.toString()
        }

        teamBScore = findViewById<TextView>(R.id.TeamBScore).apply {
            text = viewModel.ScoreB.toString()
        }

        teamAText = findViewById<TextView>(R.id.TeamAText).apply {
            text = viewModel.TeamAName
        }

        teamBText = findViewById<TextView>(R.id.TeamBText).apply {
            text = viewModel.TeamBName
        }

        setListeners(controller)
    }

    fun clickSave(view: View){
         val intent = Intent(this, ClickSave::class.java)
            intent.putExtra(scoreA, viewModel.ScoreA.toString())
            intent.putExtra(scoreB, viewModel.ScoreB.toString())
            intent.putExtra(teamBName, viewModel.TeamBName)
            intent.putExtra(teamAName, viewModel.TeamAName)
        startActivity(intent)

    }



    private fun setListeners(controller: TeamController){
        button3a.setOnClickListener {
            val score: Int = controller.getScoreA() + 3
            controller.setScoreA(score)
            teamAScore.text=viewModel.ScoreA.toString()
            Log.d(viewModel.TeamAName,"+3 points")
        }
        button2a.setOnClickListener {
            val score: Int = controller.getScoreA() + 2
            controller.setScoreA(score)
            teamAScore.text=viewModel.ScoreA.toString()
            Log.d(viewModel.TeamAName,"+2 points")

        }
        freeThrowA.setOnClickListener {
            val score: Int = controller.getScoreA() + 1
            controller.setScoreA(score)
            teamAScore.text=viewModel.ScoreA.toString()
            Log.d(viewModel.TeamAName,"+1 point")

        }
        button3b.setOnClickListener {
            val score: Int = controller.getScoreB() + 3
            controller.setScoreB(score)
            teamBScore.text=viewModel.ScoreB.toString()
            Log.d(viewModel.TeamBName,"+3 points")

        }
        button2b.setOnClickListener {
            val score: Int = controller.getScoreB() + 2
            controller.setScoreB(score)
            teamBScore.text=viewModel.ScoreB.toString()
            Log.d(viewModel.TeamBName,"+2 points")

        }
        freeThrowB.setOnClickListener {
            val score: Int = controller.getScoreB() + 1
            controller.setScoreB(score)
            teamBScore.text=viewModel.ScoreB.toString()
            Log.d(viewModel.TeamBName,"+1 point")

        }
      /*  nameGenerator.setOnClickListener {
            val teamA : String = controller.nameGenerator()
            val teamB : String = controller.nameGenerator()
            controller.setTeamAName(teamA)
            controller.setTeamBName(teamB)
            teamAText.text = viewModel.TeamAName
            teamBText.text = viewModel.TeamBName
            Log.d("NameGenerator", "Team names generated")

        }


       */

        resetButt.setOnClickListener {
            controller.setScoreA(0)
            controller.setScoreB(0)
            controller.setTeamAName("Team A")
            controller.setTeamBName("Team B")
            teamAText.text = "Team A"
            teamBText.text = "Team B"
            teamAScore.text = "0"
            teamBScore.text = "0"
            Log.d("ResetButt", "Reset button clicked")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("teamA", viewModel.TeamAName)
        outState.putString("teamB", viewModel.TeamBName)
    }
}
