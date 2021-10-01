package com.example.cs4518_project

import android.app.Application

class HistoryIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        HistoryRepository.initialize(this)
    }
}