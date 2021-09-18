package com.example.cs4518_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "HistoryListFragment"

class HistoryListFragment : Fragment() {
private lateinit var historyRecyclerView: RecyclerView
private var adapter: HistoryAdapter? = null

    private val historyListViewModel: HistoryListViewModel by lazy {
        ViewModelProvider(this).get(HistoryListViewModel::class.java)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${historyListViewModel.listofHistory.size}")
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
        private fun updateUI() {
            val crimes = historyListViewModel.listofHistory
            adapter = HistoryAdapter(crimes)
            historyRecyclerView.adapter = adapter
        }


        private inner class HistoryHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        val dateTextView: TextView = itemView.findViewById(R.id.item_detail)
    }

    private inner class HistoryAdapter(var history: List<History>)
        : RecyclerView.Adapter<HistoryHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : HistoryHolder {
            val view = layoutInflater.inflate(R.layout.card_layout, parent, false)
            return HistoryHolder(view)
        }

        override fun getItemCount() = history.size

        override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
            val histories = history[position]
            holder.apply {
                titleTextView.text = histories.title
                dateTextView.text = histories.date.toString()
            }
        }
    }

    companion object {
        fun newInstance(): HistoryListFragment {
            return HistoryListFragment()
        }
    }
}