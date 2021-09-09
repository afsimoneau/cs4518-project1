package com.example.cs4518_project1

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView


//view
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation

        setContentView(R.layout.activity_main)


        val model: Team = initTeam() //;initializing Teams at 0,0
        val view = MainActivity()
        val controller = TeamController(model, view)
        val button3a = findViewById<Button>(R.id.button3a)
        val button3b = findViewById<Button>(R.id.button3b)
        val button2b = findViewById<Button>(R.id.button2b)
        val button2a = findViewById<Button>(R.id.button2a)
        val freeThrowB = findViewById<Button>(R.id.FreeThrowB)
        val freeThrowA = findViewById<Button>(R.id.FreeThrowA)
        val resetButt = findViewById<Button>(R.id.ResetButt)
        val teamBScore: TextView = findViewById(R.id.TeamBScore)
        val teamAScore = findViewById<TextView>(R.id.TeamAScore)
        val teamAText = findViewById<TextView>(R.id.TeamAText)
        val teamBText = findViewById<TextView>(R.id.TeamBText)
        val nameGenerator = findViewById<Button>(R.id.NameGenerator)

        if(savedInstanceState != null){
            teamAText.text = savedInstanceState.getString(model.TeamAName)
            teamBText.text = savedInstanceState.getString(model.TeamBName)
            teamAScore.text = savedInstanceState.getString(model.ScoreA.toString())
            teamBScore.text = savedInstanceState.getString(model.ScoreB.toString())

        }
        var score: Int

        button3a.setOnClickListener{
            score = controller.getScoreA() + 3
            controller.setScoreA(score)
            teamAScore.text = score.toString()
        }
        button2a.setOnClickListener{
            score = controller.getScoreA() + 2
            controller.setScoreA(score)
            teamAScore.text = score.toString()
        }
        freeThrowA.setOnClickListener{
            score = controller.getScoreA() + 1
            controller.setScoreA(score)
            teamAScore.text = score.toString()
        }
        button3b.setOnClickListener{
            score = controller.getScoreB() + 3
            controller.setScoreB(score)
            teamBScore.text = model.ScoreB.toString()
        }

        button2b.setOnClickListener{
            score = controller.getScoreB() + 2
            controller.setScoreB(score)
            teamBScore.text = score.toString()
        }
        freeThrowB.setOnClickListener{
            score = controller.getScoreB() + 1
            controller.setScoreB(score)
            teamBScore.text = score.toString()

        }
        nameGenerator.setOnClickListener{
            val teamA = controller.nameGenerator()
            val teamB = controller.nameGenerator()
            controller.setTeamAName(teamA)
            controller.setTeamBName(teamB)
            teamAText.text = teamA
            teamBText.text= teamB
        }


        resetButt.setOnClickListener{
            controller.setScoreA(0)
            controller.setScoreB(0)
            controller.setTeamAName("Team A")
            controller.setTeamBName("Team B")
            teamAText.text = "Team A"
            teamBText.text = "Team B"
            teamAScore.text = "0"
            teamBScore.text = "0"
        }

    }

    private fun initTeam(): Team {
        return Team ("Team A","Team B", 0,0)
    }
    fun printDetails(nameA:String, nameB:String, scoreA: Int, scoreB: Int) {
        Log.d(nameA,"Score"+scoreA)
        Log.d(nameB,"Score"+scoreB)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }




}
