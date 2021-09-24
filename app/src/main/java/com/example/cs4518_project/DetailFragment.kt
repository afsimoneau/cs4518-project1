package com.example.cs4518_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class DetailFragment : Fragment() {
    private lateinit var viewModel: TeamViewModel
    private lateinit var teamBScore: TextView
    private lateinit var teamAScore: TextView
    private lateinit var teamAText: TextView
    private lateinit var teamBText: TextView

    private lateinit var resetButt: Button
    private lateinit var historyButt: Button
    private lateinit var saveButt: Button

    private var historyRepository: HistoryRepository = HistoryRepository.get()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(TeamViewModel::class.java)


        findViews(view)

        setOnClickListeners(view)

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

    private fun setOnClickListeners(view: View) {
        resetButt.setOnClickListener {
            val intent =
                Intent(view.context, MainActivity::class.java) //idk if this actually works
            Log.d(this::class.java.toString(), "newGameButt")

            intent.putExtra("TARGET_FRAGMENT", "SCORE")

            startActivity(intent)
        }

        historyButt.setOnClickListener {
            val intent =
                Intent(view.context, MainActivity::class.java) //idk if this actually works
            Log.d(this::class.java.toString(), "historyButt")

            intent.putExtra("TARGET_FRAGMENT", "HISTORY")

            startActivity(intent)
        }

        saveButt.setOnClickListener {
            val history = History(
                teamAName = teamAText.text as String,
                teamBName = teamBText.text as String,
                teamAScore = Integer.parseInt(teamAScore.text as String),
                teamBScore = Integer.parseInt(teamBScore.text as String)
            )
            historyRepository.updateHistory(history)
        }
    }

    private fun findViews(view: View) {
        teamAScore = view.findViewById<TextView>(R.id.teamAScore)
            .apply { text = viewModel.teamAScore.value.toString() }
        teamBScore = view.findViewById<TextView>(R.id.teamBScore)
            .apply { text = viewModel.teamBScore.value.toString() }
        teamAText = view.findViewById<TextView>(R.id.teamAText).apply { text = viewModel.teamAName.value }
        teamBText = view.findViewById<TextView>(R.id.teamBText).apply { text = viewModel.teamBName.value }

        historyButt = view.findViewById(R.id.historyButt_detail)
        saveButt = view.findViewById(R.id.saveButt_detail)
        resetButt = view.findViewById(R.id.resetButt_detail)
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

}
