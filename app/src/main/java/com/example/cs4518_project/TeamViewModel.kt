package com.example.cs4518_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TeamViewModel : ViewModel() {
    var teamAName: MutableLiveData<String> = MutableLiveData("Team A")
    var teamBName: MutableLiveData<String> = MutableLiveData("Team B")
    var teamAScore: MutableLiveData<Int> = MutableLiveData(0)
    var teamBScore: MutableLiveData<Int> = MutableLiveData(0)

    fun reset(){
        teamAName.value = "Team A"
        teamBName.value = "Team B"
        teamAScore.value = 0
        teamBScore.value = 0
    }
}