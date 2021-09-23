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
    val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS 'History' (`date` INTEGER NOT NULL, `teamAName` TEXT NOT NULL," +
                        "`teamAScore` INTEGER NOT NULL,`id` TEXT NOT NULL, `title` TEXT NOT NULL, `teamBName` TEXT NOT NULL," +
                        "`teamBScore` INTEGER NOT NULL, PRIMARY KEY(`id`))"

            )
        }
    }
