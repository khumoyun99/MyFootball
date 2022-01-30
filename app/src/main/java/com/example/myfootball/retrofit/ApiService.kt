package com.example.myfootball.retrofit

import com.example.myfootball.models.club.FootballClub
import com.example.myfootball.models.leaguses.LeaguesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

   @GET("leagues")
   suspend fun getAllLeagues():Response<LeaguesData>

   @GET("leagues/"+"{id}"+"/standings")
   suspend fun getItemFootballClub(
      @Path("id") id: String,
      @Query("season") season:String="2021",
      @Query("sort") sort:String="asc"
   ):Response<FootballClub>

}