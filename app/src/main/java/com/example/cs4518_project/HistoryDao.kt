package com.example.cs4518_project

import androidx.room.Dao
import androidx.room.Query
import com.example.cs4518_project.History
import java.util.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getHistory(): List<History>

    @Query("SELECT * FROM history WHERE id =(:id)")
    fun getHistory(id: UUID): History?
}