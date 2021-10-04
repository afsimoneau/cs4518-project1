package com.example.cs4518_project

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.io.File
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "game-database"

class HistoryRepository private constructor(context: Context) {
    private val database: HistoryDatabase =
        Room.databaseBuilder(
            context.applicationContext, HistoryDatabase::class.java, DATABASE_NAME
        )
            .build()
    private val executor = Executors.newSingleThreadExecutor()
    private val historyDao = database.HistoryDao()
    private val filesDir = context.applicationContext.filesDir

    fun getHistories(): LiveData<List<History>> = historyDao.getHistories()

    fun getHistoriesOfWinner(winner:String): LiveData<List<History>> {
        when (winner){
            "TEAM_A"->return historyDao.getHistoriesOfTeamA()
            "TEAM_B"->return historyDao.getHistoriesOfTeamB()
            else -> return historyDao.getHistories()
        }
    }


    fun getHistory(historyId: UUID): LiveData<History> = historyDao.getHistory(historyId)


    fun addHistory(history: History) {
        executor.execute {
            historyDao.addHistory(history)
        }
    }

    fun updateHistory(history: History) {
        executor.execute {
            historyDao.updateHistory(history)
        }
    }


    companion object {
        private var INSTANCE: HistoryRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = HistoryRepository(context)
            }
        }

        fun get(): HistoryRepository {
            return INSTANCE ?: throw IllegalStateException("HistoryRepository must be initialized")
        }
    }
}

