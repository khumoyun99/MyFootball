package com.example.myfootball.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.example.myfootball.database.entity.FootballClubEntity

@Dao
interface FootballClubDao {

    @Insert(onConflict = REPLACE)
    fun insert(footballClubEntity: FootballClubEntity)

}