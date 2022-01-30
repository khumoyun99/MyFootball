package com.example.myfootball.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FootballClubEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val league_name:String,
    val season_display:String,


//    val stats: List<StatEntity>,
//    val team: TeamEntity
)
