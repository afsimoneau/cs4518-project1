package com.example.cs4518_project

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cs4518_project.History
import com.example.cs4518_project.HistoryDao
import com.example.cs4518_project.HistoryTypeConverters


@Database(entities = [ History::class ], version=1)
@TypeConverters(HistoryTypeConverters::class)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun HistoryDao() : HistoryDao
}