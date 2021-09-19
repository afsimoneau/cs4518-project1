package com.example.cs4518_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.app.Activity
import androidx.lifecycle.ViewModelProvider


class ScoreFragment : Fragment() {
    private lateinit var displayButton: Button
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_score, container, false)
        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        val MainActivity = MainActivity()
        val controller = TeamController(
            viewModel
        ) //idk if i can just passin MainActivity like this...
        val orientation = resources.configuration.orientation
        /* if (orientation == 1) {
            setContentView(MainActivity, R.layout.activity_main)
        } else {
            setContentView(MainActivity, R.layout.activity_landscape)
        }

        */
        if (savedInstanceState != null) {
            viewModel.teamAName = savedInstanceState.getString("teamA").toString()
            viewModel.teamBName = savedInstanceState.getString("teamB").toString()
        } else {
            viewModel.teamAName = "Team A"
            viewModel.teamBName = "Team B"
        }

        button3a = view.findViewById(R.id.button3a)
        button3b = view.findViewById(R.id.button3b)
        button2a = view.findViewById(R.id.button2a)
        button2b = view.findViewById(R.id.button2b)
        freeThrowB = view.findViewById(R.id.freeThrowB)
        freeThrowA = view.findViewById(R.id.freeThrowA)
        resetButt = view.findViewById(R.id.resetButt)

        displayButton = view.findViewById(R.id.displayButt)
        save = view.findViewById(R.id.saveButt)

        displayButton.setOnClickListener {
            Log.d("log", "buttonWorking")
        }
        save.setOnClickListener {
            Log.d("log", "savebuttonWorking")

        }
        teamAScore = view.findViewById<TextView>(R.id.teamAScore).apply {
            text = viewModel.scoreA.toString()
        }

        teamBScore = view.findViewById<TextView>(R.id.teamBScore).apply {
            text = viewModel.scoreB.toString()
        }

        teamAText = view.findViewById<TextView>(R.id.teamAText).apply {
            text = viewModel.teamAName
        }

        teamBText = view.findViewById<TextView>(R.id.teamBText).apply {
            text = viewModel.teamBName
        }

        setListeners(controller)
        return view

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            Log.d("Error", "Did not properly receive request")
            return
        }

        if (requestCode == REQUEST) {
            Log.d("Success", "Back button clicked")

        }

    }

    private fun setListeners(controller: TeamController) {
        button3a.setOnClickListener {
            val score: Int = controller.getScoreA() + 3
            controller.setScoreA(score)
            teamAScore.text = viewModel.scoreA.toString()
            Log.d(viewModel.teamAName, "+3 points")
        }
        button2a.setOnClickListener {
            val score: Int = controller.getScoreA() + 2
            controller.setScoreA(score)
            teamAScore.text = viewModel.scoreA.toString()
            Log.d(viewModel.teamAName, "+2 points")

        }
        freeThrowA.setOnClickListener {
            val score: Int = controller.getScoreA() + 1
            controller.setScoreA(score)
            teamAScore.text = viewModel.scoreA.toString()
            Log.d(viewModel.teamAName, "+1 point")

        }
        button3b.setOnClickListener {
            val score: Int = controller.getScoreB() + 3
            controller.setScoreB(score)
            teamBScore.text = viewModel.scoreB.toString()
            Log.d(viewModel.teamBName, "+3 points")

        }
        button2b.setOnClickListener {
            val score: Int = controller.getScoreB() + 2
            controller.setScoreB(score)
            teamBScore.text = viewModel.scoreB.toString()
            Log.d(viewModel.teamBName, "+2 points")

        }
        freeThrowB.setOnClickListener {
            val score: Int = controller.getScoreB() + 1
            controller.setScoreB(score)
            teamBScore.text = viewModel.scoreB.toString()
            Log.d(viewModel.teamBName, "+1 point")

        }
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
        save.setOnClickListener {
            val intent = Intent(view?.context, ClickSave::class.java) //idk if this actually works
            Log.d("log", "savebuttonWorking")

            intent.putExtra(scoreA, viewModel.scoreA.toString())
            intent.putExtra(scoreB, viewModel.scoreB.toString())
            intent.putExtra(teamBName, viewModel.teamBName)
            intent.putExtra(teamAName, viewModel.teamAName)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("teamA", viewModel.teamAName)
        outState.putString("teamB", viewModel.teamBName)
    }

}



