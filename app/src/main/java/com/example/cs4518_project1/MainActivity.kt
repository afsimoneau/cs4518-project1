package com.example.cs4518_project1

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button;
import android.widget.TextView
import android.widget.Toast
import com.example.cs4518_project1.databinding.ActivityMainBinding
import org.w3c.dom.Text


//view
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var model: Team = initTeam() //;initalizing Teams at 0,0
        var view = MainActivity()
        var controller = TeamController(model, view)
        var button3a = findViewById<Button>(R.id.button3a)
        var button3b = findViewById<Button>(R.id.button3b)
        var button2b = findViewById<Button>(R.id.button2b)
        var button2a = findViewById<Button>(R.id.button2a)
        var FreeThrowB = findViewById<Button>(R.id.FreeThrowB)
        var FreeThrowA = findViewById<Button>(R.id.FreeThrowA)
        var ResetButt = findViewById<Button>(R.id.ResetButt)
        val TeamBScore: TextView = findViewById(R.id.TeamBScore)
        val TeamAScore = findViewById<TextView>(R.id.TeamAScore)
        var score: Int
        controller.setScoreA(0);
        controller.setScoreB(0);

        button3a.setOnClickListener{
            score = controller.getScoreA() + 3
            controller.setScoreA(score)
            TeamAScore.text = score.toString()
        }
        button2a.setOnClickListener{
            score = controller.getScoreA() + 2
            controller.setScoreA(score)
            TeamAScore.text = score.toString()
        }
        FreeThrowA.setOnClickListener{
            score = controller.getScoreA() + 1
            controller.setScoreA(score)
            TeamAScore.text = score.toString()
        }
        button3b.setOnClickListener{
            score = controller.getScoreB() + 3
            controller.setScoreB(score)
            TeamBScore.text = model.ScoreB.toString()
        }

        button2b.setOnClickListener{
            score = controller.getScoreB() + 2
            controller.setScoreB(score)
            TeamBScore.text = score.toString()
        }
        FreeThrowB.setOnClickListener{
            score = controller.getScoreB() + 1
            controller.setScoreB(score)
            TeamBScore.text = score.toString()

        }
        ResetButt.setOnClickListener{
            controller.setScoreA(0);
            controller.setScoreB(0);
            TeamAScore.text = "0"
            TeamBScore.text = "0"

        }

    }
    fun initTeam(): Team {
        return Team (0,0)
    }
    fun printDetails(scoreA: Int, scoreB: Int) {
        Log.d("TeamA","Score"+scoreA)
        Log.d("TeamB","Score"+scoreB)

    }




}
