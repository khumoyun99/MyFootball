package com.example.myfootball.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfootball.models.club.Logo

@Entity
class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    val _id:Int=0,
    val displayName: String,
    val abbreviation: String,
    val shortDisplayName: String,
    val logos_href:String
)