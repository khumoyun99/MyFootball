package com.example.myfootball.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class StatEntity(
    @PrimaryKey(autoGenerate = true)
    val _id:Int=0,
    val value: Int,
    val name: String,
    val displayName: String,
    val shortDisplayName: String,
    val description: String,
    val abbreviation: String,
    val type: String,
    val displayValue: String,
    val id: String?,
    val summary: String?

)