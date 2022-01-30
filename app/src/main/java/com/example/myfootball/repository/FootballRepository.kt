package com.example.myfootball.repository

import com.example.myfootball.database.dao.LeagueDao
import com.example.myfootball.database.entity.LeagueEntity
import com.example.myfootball.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class FootballRepository(private val apiService: ApiService,private val leagueDao: LeagueDao ) {

    suspend fun getALlLeagues() = flow { emit(apiService.getAllLeagues()) }

//    suspend fun getDbLeagues() = flow { emit(leagueDao.getAllLeague()) }

//    suspend fun addLeagueToDb(list:List<LeagueEntity>) = leagueDao.insert(list)

    suspend fun getItemFootballClub(id:String) = flow { emit(apiService.getItemFootballClub(id)) }
}