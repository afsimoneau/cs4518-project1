package com.example.cs4518_project

import kotlin.random.Random

class TeamController(var teamViewModel: TeamViewModel) {
    fun getScoreA(): Int {
        return teamViewModel.teamAScore.value!!
    }

    fun getScoreB(): Int {
        return teamViewModel.teamBScore.value!!
    }

    fun setScoreA(scoreA: Int) {
        teamViewModel.teamAScore.value = scoreA
    }

    fun setScoreB(scoreB: Int) {
        teamViewModel.teamBScore.value = scoreB
    }

    fun setTeamAName(nameA: String) {
        teamViewModel.teamAName.value = nameA
    }

    fun setTeamBName(nameB: String) {
        teamViewModel.teamBName.value = nameB
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