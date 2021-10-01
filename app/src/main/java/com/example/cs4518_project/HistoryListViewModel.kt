package com.example.cs4518_project

import androidx.lifecycle.ViewModel

class HistoryListViewModel : ViewModel() {
    private val historyRepository = HistoryRepository.get()
    var historyLiveData = historyRepository.getHistories()
}