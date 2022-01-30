package com.example.myfootball.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myfootball.database.entity.LeagueEntity

@Dao
interface LeagueDao {

//    @Insert()
//    fun insert(leagueEntity: LeagueEntity)

//    @Insert(onConflict = REPLACE)
//    suspend fun insert(leagueEntityList: List<LeagueEntity>)

//    @Query("select * from leagueentity")
//    suspend fun getAllLeague() : List<LeagueEntity>

}