package com.example.cs4518_project

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cs4518_project.History
import com.example.cs4518_project.HistoryDao
import com.example.cs4518_project.HistoryTypeConverters


@Database(entities = [ History::class ], version=2)
@TypeConverters(HistoryTypeConverters::class)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun HistoryDao(): HistoryDao
}
