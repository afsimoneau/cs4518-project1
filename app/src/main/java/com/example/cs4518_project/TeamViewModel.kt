package com.example.cs4518_project

import androidx.lifecycle.ViewModel

class TeamViewModel : ViewModel() {
    lateinit var teamAName: String
    lateinit var teamBName: String
    var scoreA: Int = 0
    var scoreB: Int = 0
}