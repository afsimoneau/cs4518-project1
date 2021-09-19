package com.example.cs4518_project

import kotlin.random.Random

class TeamController(var teamViewModel: TeamViewModel) {
    fun getScoreA(): Int {
        return teamViewModel.scoreA
    }

    fun getScoreB(): Int {
        return teamViewModel.scoreB
    }

    fun setScoreA(scoreA: Int) {
        teamViewModel.scoreA = scoreA
    }

    fun setScoreB(scoreB: Int) {
        teamViewModel.scoreB = scoreB
    }

    fun setTeamAName(nameA: String) {
        teamViewModel.teamAName = nameA
    }

    fun setTeamBName(nameB: String) {
        teamViewModel.teamBName = nameB
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