package com.example.cs4518_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.io.File
import java.util.*

class HistoryDetailViewModel : ViewModel() {
    private val historyRepository = HistoryRepository.get()
    private val historyIdLiveData = MutableLiveData<UUID>()

    var historyLiveData: LiveData<History> =
        Transformations.switchMap(historyIdLiveData) { historyId ->
            historyRepository.getHistory(historyId)
        }

    fun loadHistory(historyId: UUID) {
        historyIdLiveData.value = historyId
    }

    fun saveHistory(history: History) {
        historyRepository.updateHistory(history)
    }

}