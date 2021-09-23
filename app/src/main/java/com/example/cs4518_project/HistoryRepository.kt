package com.example.cs4518_project

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*

private const val  DATABASE_NAME = "game-database"

class HistoryRepository private constructor(context : Context) {
    private val database: HistoryDatabase =
        Room.databaseBuilder(
            context.applicationContext, HistoryDatabase::class.java, DATABASE_NAME).addMigrations(migration_1_2)
            .build()
    private val historyDao = database.HistoryDao()
    fun getHistory(): LiveData<List<History>> = historyDao.getHistory()
    fun getHistoryteamID(id:UUID): LiveData<List<History?>> = historyDao.getHistoryteamID(id)
    fun getHistoryteamAName(teamAName:String): LiveData<List<History>> = historyDao.getHistoryteamAName(teamAName)
    fun getHistoryteamBName(teamBName:String): LiveData<List<History>> = historyDao.getHistoryteamBName(teamBName)
    fun getHistoryteamAScore(teamAScore:Int): LiveData<List<History>> = historyDao.getHistoryteamAScore(teamAScore)
    fun getHistoryteamBScore(teamBScore:Int):LiveData<List<History>> = historyDao.getHistoryteamBScore(teamBScore)

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

