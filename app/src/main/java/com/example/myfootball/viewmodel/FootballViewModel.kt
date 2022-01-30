package com.example.myfootball.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfootball.database.entity.LeagueEntity
import com.example.myfootball.models.club.FootballClub
import com.example.myfootball.models.club.Standing
import com.example.myfootball.models.leaguses.Data
import com.example.myfootball.repository.FootballRepository
import com.example.myfootball.utils.FootballResource
import com.example.myfootball.utils.ItemFootballClubResource
import com.example.myfootball.utils.NetworkHelper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FootballViewModel(
    private val footballRepository: FootballRepository,
    private val networkHelper: NetworkHelper
):ViewModel() {

    init {
        fetchFootball()
    }
    fun fetchFootball():StateFlow<ItemFootballClubResource>{
        val stateFlow = MutableStateFlow<ItemFootballClubResource>(ItemFootballClubResource.Loading)

        viewModelScope.launch {
            if(networkHelper.isNetworkConnected()){
                footballRepository.getALlLeagues()
                    .catch {
                        stateFlow.emit(ItemFootballClubResource.Error(it.message?:""))
                    }.collect {
                        if(it.isSuccessful){
                            val leaguesData1 = it.body()
                            val data = ArrayList(leaguesData1?.data)


                            val footBallClubList = ArrayList<FootballClub>()
                            data.forEach { d->
                                footballRepository.getItemFootballClub(d.id)
                                    .catch {

                                    }
                                    .collect { res->
                                        if(res.isSuccessful){
                                            val body = res.body()
                                            if (body != null) {
                                                body.logo = d.logos.light
                                                footBallClubList.add(body)
                                            }
                                        }
                                    }
                            }
                            stateFlow.emit(ItemFootballClubResource.Success(footBallClubList))

                        }else{
                            stateFlow.emit(ItemFootballClubResource.Error("Client or Server error!"))
                        }
                    }

            }else{
//                footballRepository.getDbLeagues()
//                    .collect {
//                        if(it.isEmpty()){
//                            stateFlow.emit(ItemFootballClubResource.Error("No Internet connection"))
//                        }else{
////                            stateFlow.emit(FootballResource.Success(it))
//                        }
//                    }
                stateFlow.emit(ItemFootballClubResource.Error("No Internet connection"))
            }
        }

        return stateFlow
    }

//    fun itemFootballClub(id:String):StateFlow<ItemFootballClubResource>{
//        val stateFlow1 = MutableStateFlow<ItemFootballClubResource>(ItemFootballClubResource.Loading)
//
//        viewModelScope.launch {
//            if(networkHelper.isNetworkConnected()){
//                footballRepository.getItemFootballClub(id)
//                    .catch {
//                        stateFlow1.emit(ItemFootballClubResource.Error(it.message?:""))
//                    }
//                    .collect {
//                        if(it.isSuccessful){
//                            val body = it.body()
//                            stateFlow1.emit(ItemFootballClubResource.Success(body?.data?.standings as ArrayList<Standing>))
//                        }else{
//                            stateFlow1.emit(ItemFootballClubResource.Error("Client or Server error!:${it.errorBody()}"))
//                        }
//                    }
//
//            }else{
//                stateFlow1.emit(ItemFootballClubResource.Error("No Internet connection"))
//            }
//
//        }
//
//        return stateFlow1
//    }

}