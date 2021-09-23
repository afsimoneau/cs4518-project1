package com.example.cs4518_project

import android.util.Log
import androidx.lifecycle.ViewModel

class HistoryListViewModel : ViewModel() {
    private val historyRepository = HistoryRepository.get()
    val historyLiveData = historyRepository.getHistory()
}