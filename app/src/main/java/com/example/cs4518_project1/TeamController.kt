package com.example.cs4518_project1

class TeamController(var model: Team, var view: MainActivity ) {
    fun getScoreA(): Int{
        return model.ScoreA
    }
    fun getScoreB(): Int{
        return model.ScoreB
    }
    fun setScoreA(scoreA: Int ){
        model.ScoreA=scoreA
    }
    fun setScoreB(scoreB: Int){
        model.ScoreB=scoreB
    }

    fun updateView(){
        view.printDetails(model.ScoreA, model.ScoreB)
    }
}