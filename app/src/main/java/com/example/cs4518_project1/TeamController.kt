package com.example.cs4518_project1

import android.os.Bundle
import kotlin.random.Random

class TeamController(var model: Team, var view: MainActivity ) {
    fun getScoreA(): Int {
        return model.ScoreA
    }

    fun getScoreB(): Int {
        return model.ScoreB
    }

    fun getTeamAName(): String {
        return model.TeamAName
    }

    fun getTeamBName(): String {
        return model.TeamBName
    }

    fun setScoreA(scoreA: Int) {
        model.ScoreA = scoreA
    }

    fun setScoreB(scoreB: Int) {
        model.ScoreB = scoreB
    }

    fun setTeamAName(nameA: String) {
        model.TeamAName = nameA
    }

    fun setTeamBName(nameB: String) {
        model.TeamBName = nameB
    }

    fun updateView() {
        view.printDetails(model.TeamAName, model.TeamBName, model.ScoreA, model.ScoreB)
    }

    fun nameGenerator(): String {
        val color = listOf(
            "black",
            "white",
            "gray",
            "silver",
            "maroon",
            "red",
            "purple",
            "fushsia",
            "green",
            "lime",
            "olive",
            "yellow",
            "navy",
            "blue",
            "teal",
            "aqua"
        )
        val animal = listOf(
            "giraffes",
            "foxes",
            "tigers",
            "chimpanzees",
            "chimpmunks",
            "koalas",
            "deer",
            "monkeys",
            "elephants",
            "kangaroos",
            "gorillas",
            "leopards",
            "rhinoceros",
            "rabbits",
            "mice"
        )
        val randomIndexC = Random.nextInt(color.size)
        val randomIndexA = Random.nextInt(animal.size)
        val final = color[randomIndexC] + " " + animal[randomIndexA]
        return final

    }
}