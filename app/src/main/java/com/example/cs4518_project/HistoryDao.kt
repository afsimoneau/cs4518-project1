package com.example.cs4518_project

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getHistories(): LiveData<List<History>>

    @Query("SELECT * FROM history WHERE id =(:id)")
    fun getHistory(id: UUID): LiveData<History>

    @Query("SELECT * FROM history WHERE teamAScore > teamBScore ")
    fun getHistoriesOfTeamA():LiveData<List<History>>

    @Query("SELECT * FROM history WHERE teamAScore < teamBScore ")
    fun getHistoriesOfTeamB():LiveData<List<History>>

    @Insert()
    fun addHistory(history: History?)

    @Update()
    fun updateHistory(history: History?)
}