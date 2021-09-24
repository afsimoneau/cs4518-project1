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
    private lateinit var historyButt: Button
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
    private lateinit var saveButt: Button

    private var historyRepository: HistoryRepository = HistoryRepository.get()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.fragment_score, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(TeamViewModel::class.java)
        val controller = TeamController(viewModel)


        findViews(view)

        setListeners(controller)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TeamViewModel::class.java)

        viewModel.teamAScore.observe(viewLifecycleOwner,{
            teamAScore.text = it.toString()
        })
        viewModel.teamBScore.observe(viewLifecycleOwner,{
            teamBScore.text = it.toString()
        })
        viewModel.teamAName.observe(viewLifecycleOwner,{
            teamAText.text = it
        })
        viewModel.teamBName.observe(viewLifecycleOwner,{
            teamBText.text = it
        })
    }

    private fun findViews(view: View) {
        button3a = view.findViewById(R.id.button3a)
        button3b = view.findViewById(R.id.button3b)
        button2a = view.findViewById(R.id.button2a)
        button2b = view.findViewById(R.id.button2b)
        freeThrowB = view.findViewById(R.id.freeThrowB)
        freeThrowA = view.findViewById(R.id.freeThrowA)
        resetButt = view.findViewById(R.id.resetButt_score)
        historyButt = view.findViewById(R.id.historyButt_score)
        saveButt = view.findViewById(R.id.saveButt_score)

        teamAScore = view.findViewById<TextView>(R.id.teamAScore).apply {
            text = viewModel.teamAScore.value.toString()
        }

        teamBScore = view.findViewById<TextView>(R.id.teamBScore).apply {
            text = viewModel.teamBScore.value.toString()
        }

        teamAText = view.findViewById<TextView>(R.id.teamAText).apply {
            text = viewModel.teamAName.value
        }

        teamBText = view.findViewById<TextView>(R.id.teamBText).apply {
            text = viewModel.teamBName.value
        }
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
            teamAScore.text = viewModel.teamAScore.value.toString()
            Log.d(viewModel.teamAName.value, "+3 points")
        }
        button2a.setOnClickListener {
            val score: Int = controller.getScoreA() + 2
            controller.setScoreA(score)
            teamAScore.text = viewModel.teamAScore.value.toString()
            Log.d(viewModel.teamAName.value, "+2 points")

        }
        freeThrowA.setOnClickListener {
            val score: Int = controller.getScoreA() + 1
            controller.setScoreA(score)
            teamAScore.text = viewModel.teamAScore.value.toString()
            Log.d(viewModel.teamAName.value, "+1 point")

        }
        button3b.setOnClickListener {
            val score: Int = controller.getScoreB() + 3
            controller.setScoreB(score)
            teamBScore.text = viewModel.teamBScore.value.toString()
            Log.d(viewModel.teamBName.value, "+3 points")

        }
        button2b.setOnClickListener {
            val score: Int = controller.getScoreB() + 2
            controller.setScoreB(score)
            teamBScore.text = viewModel.teamBScore.value.toString()
            Log.d(viewModel.teamBName.value, "+2 points")

        }
        freeThrowB.setOnClickListener {
            val score: Int = controller.getScoreB() + 1
            controller.setScoreB(score)
            teamBScore.text = viewModel.teamBScore.value.toString()
            Log.d(viewModel.teamBName.value, "+1 point")

        }
        resetButt.setOnClickListener {
            Log.d(this::class.java.toString(), "ResetButt")

            controller.setScoreA(0)
            controller.setScoreB(0)
            controller.setTeamAName("Team A")
            controller.setTeamBName("Team B")
            teamAText.text = "Team A"
            teamBText.text = "Team B"
            teamAScore.text = "0"
            teamBScore.text = "0"
            viewModel.reset()
        }
        saveButt.setOnClickListener {
            Log.d(this::class.java.toString(), "SaveButt")

            val history = History(
                teamAName = teamAText.text as String,
                teamBName = teamBText.text as String,
                teamAScore = Integer.parseInt(teamAScore.text as String),
                teamBScore = Integer.parseInt(teamBScore.text as String)
            )
            historyRepository.addHistory(history)

            val intent = Intent(view?.context, MainActivity::class.java)
            intent.putExtra("TARGET_FRAGMENT", "DETAIL")

            startActivity(intent)
        }
        historyButt.setOnClickListener {
            Log.d(this::class.java.toString(), "HistoryButt")

            val history = History(
                teamAName = teamAText.text as String,
                teamBName = teamBText.text as String,
                teamAScore = Integer.parseInt(teamAScore.text as String),
                teamBScore = Integer.parseInt(teamBScore.text as String)
            )
            historyRepository.addHistory(history)

            val intent = Intent(view?.context, MainActivity::class.java)
            intent.putExtra("TARGET_FRAGMENT", "HISTORY")

            startActivity(intent)
        }
    }

}



