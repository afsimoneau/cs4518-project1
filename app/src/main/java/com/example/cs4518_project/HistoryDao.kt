package com.example.cs4518_project

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.cs4518_project.History
import java.util.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getHistory(): LiveData<List<History>>

    @Query("SELECT * FROM history WHERE teamAName =(:teamAName)")
    fun getHistoryteamAName(teamAName:String): LiveData<List<History>>

    @Query("SELECT * FROM history WHERE teamAName =(:teamBName)")
    fun getHistoryteamBName(teamBName:String): LiveData<List<History>>

    @Query("SELECT * FROM history WHERE teamAScore =(:teamAScore)")
    fun getHistoryteamAScore(teamAScore:Int): LiveData<List<History>>

    @Query("SELECT * FROM history WHERE teamBScore =(:teamBScore)")
    fun getHistoryteamBScore(teamBScore:Int): LiveData<List<History>>

    @Query("SELECT * FROM history WHERE id =(:id)")
    fun getHistoryteamID(id: UUID): LiveData<List<History?>>

}