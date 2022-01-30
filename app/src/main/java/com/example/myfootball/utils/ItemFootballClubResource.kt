package com.example.myfootball.utils

import com.example.myfootball.models.club.FootballClub
import com.example.myfootball.models.club.Standing

sealed class ItemFootballClubResource {

    object Loading:ItemFootballClubResource()

    data class Success(val list:ArrayList<FootballClub>):ItemFootballClubResource()

    data class Error(val message:String):ItemFootballClubResource()
}