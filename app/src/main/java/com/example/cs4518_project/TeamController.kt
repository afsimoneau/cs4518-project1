package com.example.cs4518_project

import kotlin.random.Random

class TeamController(var teamViewModel: TeamViewModel, var view: MainActivity ) {
    fun getScoreA(): Int {
        return teamViewModel.ScoreA
    }

    fun getScoreB(): Int {
        return teamViewModel.ScoreB
    }

    fun getTeamAName(): String {
        return teamViewModel.TeamAName
    }

    fun getTeamBName(): String {
        return teamViewModel.TeamBName
    }

    fun setScoreA(scoreA: Int) {
        teamViewModel.ScoreA = scoreA
    }

    fun setScoreB(scoreB: Int) {
        teamViewModel.ScoreB = scoreB
    }

    fun setTeamAName(nameA: String) {
        teamViewModel.TeamAName = nameA
    }

    fun setTeamBName(nameB: String) {
        teamViewModel.TeamBName = nameB
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
        return color[randomIndexC] + " " + animal[randomIndexA]

    }
}