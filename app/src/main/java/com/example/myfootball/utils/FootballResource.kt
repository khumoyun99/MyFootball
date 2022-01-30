package com.example.myfootball.utils

import com.example.myfootball.models.leaguses.Data

sealed class FootballResource {

    object Loading:FootballResource()

    data class Success(val list:List<Data>):FootballResource()

    data class Error(val message:String):FootballResource()
}