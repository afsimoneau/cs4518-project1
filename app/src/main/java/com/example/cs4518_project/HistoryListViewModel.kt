package com.example.cs4518_project

import android.util.Log
import androidx.lifecycle.ViewModel

class HistoryListViewModel : ViewModel(){
    val listofHistory = mutableListOf<History>()
    init{
        for (i in 0 until 100){
            val history = History()
            history.title = "History #$i"
            listofHistory += history
            Log.d("history $i", history.toString())
        }
    }
}