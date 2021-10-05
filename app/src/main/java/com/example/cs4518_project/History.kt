package com.example.cs4518_project

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class History(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "", var date: Date = Date(),
    var teamAName: String = "", var teamBName: String = "",
    var teamBScore: Int = 0, var teamAScore: Int = 0,
    var teamAPhoto: Uri = Uri.parse("IMG_$id-a.jpg"),
    var teamBPhoto: Uri = Uri.parse("IMG_$id-b.jpg")
)