package com.example.cs4518_project

import android.content.Context
import androidx.room.Room
import java.util.*

private const val  DATABASE_NAME = "history-database"

class HistoryRepository private constructor(context : Context) {
    private val database: HistoryDatabase =
        Room.databaseBuilder(
            context.applicationContext, HistoryDatabase::class.java, DATABASE_NAME).build()
    private val historyDao = database.HistoryDao()
    fun getHistory(): List<History> = historyDao.getHistory()
    fun getHistory(id:UUID):History? = historyDao.getHistory(id)


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