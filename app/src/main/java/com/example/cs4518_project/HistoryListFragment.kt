package com.example.cs4518_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HistoryListFragment : Fragment() {
    private lateinit var historyRecyclerView: RecyclerView
    private var adapter: HistoryAdapter? = HistoryAdapter(emptyList())

    private val historyListViewModel: HistoryListViewModel by lazy {
        ViewModelProvider(this).get(HistoryListViewModel::class.java)
    }

    private lateinit var viewModel: TeamViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(TeamViewModel::class.java)

        historyRecyclerView =
            view.findViewById(R.id.historyRecyclerView) as RecyclerView
        historyRecyclerView.layoutManager = LinearLayoutManager(context)
        historyRecyclerView.adapter = adapter

        var resetButt = view.findViewById<Button>(R.id.resetButt_history)
        resetButt.setOnClickListener {
            Log.d(this::class.java.toString(), "ResetButt")

            val intent = Intent(view?.context, MainActivity::class.java)
            intent.putExtra("TARGET_FRAGMENT", "SCORE")

            viewModel.reset()
            startActivity(intent)
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyListViewModel.historyLiveData.observe(viewLifecycleOwner, { histories ->
            histories?.let {
                Log.d(this::class.java.toString(), "History ${histories.size}")

                updateUI(histories)
            }
        })
    }

    private inner class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var history: History
        private val titleTextView: TextView = itemView.findViewById(R.id.list_item_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.list_item_detail)
        private val winnerImage: ImageView = itemView.findViewById(R.id.list_item_image)

        fun bind(history: History) {
            this.history = history
            titleTextView.text = this.history.title
            dateTextView.text = this.history.date.toString()
            if (history.teamAScore > history.teamBScore) {
                winnerImage.setImageResource(R.drawable.basketball);
            } else {
                winnerImage.setImageResource(R.drawable.basketball2)

            }
        }
    }

    private inner class HistoryAdapter(var listOfHistory: List<History>) :
        RecyclerView.Adapter<HistoryHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
            val view = layoutInflater.inflate(R.layout.list_item_history, parent, false)
            return HistoryHolder(view)
        }

        override fun getItemCount() = listOfHistory.size

        override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
            val history = listOfHistory[position]
            holder.bind(history)

        }
    }


    private fun updateUI(histories: List<History>) {
        adapter = HistoryAdapter(histories)
        historyRecyclerView.adapter = adapter
    }


    companion object {
        fun newInstance(): HistoryListFragment {
            return HistoryListFragment()
        }
    }
}