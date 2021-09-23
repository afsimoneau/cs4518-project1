package com.example.cs4518_project

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

private const val DATABASE_NAME = "game-database"

class HistoryRepository private constructor(context : Context) {
    private val database: HistoryDatabase =
        Room.databaseBuilder(
            context.applicationContext, HistoryDatabase::class.java, DATABASE_NAME)
            .build()
    private val executor = Executors.newSingleThreadExecutor()

    private val historyDao = database.HistoryDao()


    fun getHistory(): LiveData<List<History>> = historyDao.getHistory()
    fun addHistory(history:History) {
        executor.execute{
            historyDao.addHistory(history)
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
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}

