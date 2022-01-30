package com.example.myfootball.models.club

import java.io.Serializable

data class FootballClub(
    val `data`: Data,
    val status: Boolean,
    var logo:String?=null
):Serializable