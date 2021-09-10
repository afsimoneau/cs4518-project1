package com.example.cs4518_project1

import androidx.lifecycle.ViewModel

class TeamViewModel: ViewModel(){
    lateinit var TeamAName:String
    lateinit var TeamBName:String
    var ScoreA:Int = 0
    var ScoreB:Int = 0
}