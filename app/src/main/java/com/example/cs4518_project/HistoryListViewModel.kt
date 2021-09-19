package com.example.cs4518_project

import android.util.Log
import androidx.lifecycle.ViewModel

class HistoryListViewModel : ViewModel() {
    val listofHistory = mutableListOf<History>()
    val historySize = 100

    init {
        Log.d("HistoryListViewModel","Size: $historySize")
        for (i in 0 until historySize) {
            val history = History()
            history.title = "History #$i"
            listofHistory += history
        }
    }
}