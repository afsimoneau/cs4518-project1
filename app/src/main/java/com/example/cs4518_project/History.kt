package com.example.cs4518_project

import java.util.*

data class History(
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date()
)