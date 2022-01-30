package com.example.myfootball.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfootball.database.dao.FootballClubDao
import com.example.myfootball.database.dao.LeagueDao
import com.example.myfootball.database.entity.FootballClubEntity
import com.example.myfootball.database.entity.LeagueEntity

@Database(entities = [LeagueEntity::class,FootballClubEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun leagueDao():LeagueDao
    abstract fun footballDao():FootballClubDao

    companion object{
        private var appDatabase:AppDatabase?=null

        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            if(appDatabase==null){
                appDatabase = Room.databaseBuilder(context,AppDatabase::class.java,"my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase!!
        }
    }
}