package com.example.cs4518_project

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class History(@PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date()
)