package com.example.myfootball.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LeagueEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val league_id:String,
    val league_name:String,
    val abbr:String,
    val logo:String

)