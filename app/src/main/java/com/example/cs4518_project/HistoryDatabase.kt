package com.example.cs4518_project

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [History::class], version = 2)
@TypeConverters(HistoryTypeConverters::class)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun HistoryDao(): HistoryDao
}
