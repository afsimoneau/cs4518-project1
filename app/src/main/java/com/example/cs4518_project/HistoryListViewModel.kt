package com.example.cs4518_project

import androidx.lifecycle.ViewModel

class HistoryListViewModel : ViewModel(){
    val listofHistory = mutableListOf<History>()
    init{
        for (i in 0 until 20){
            val history = History()
            history.title = "History #$i"
            listofHistory += listofHistory
        }
    }
}