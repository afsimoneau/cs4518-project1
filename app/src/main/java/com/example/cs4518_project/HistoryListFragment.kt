package com.example.cs4518_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryListFragment : Fragment() {
    private lateinit var historyRecyclerView: RecyclerView
    private var adapter: HistoryAdapter? = null

    private val historyListViewModel: HistoryListViewModel by lazy {
        ViewModelProvider(this).get(HistoryListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this::class.java.toString(), "Total history: ${historyListViewModel.listofHistory.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        historyRecyclerView =
            view.findViewById(R.id.recyclerSummary) as RecyclerView
        historyRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()

        return view

    }

    private inner class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var history: History
        private val titleTextView: TextView = itemView.findViewById(R.id.list_item_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.list_item_detail)

        fun bind(history: History) {
            this.history = history
            titleTextView.text = this.history.title
            dateTextView.text = this.history.date.toString()
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

    private fun updateUI() {
        val listOfHistory = historyListViewModel.listofHistory
        adapter = HistoryAdapter(listOfHistory)
        historyRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): HistoryListFragment {
            return HistoryListFragment()
        }
    }
}