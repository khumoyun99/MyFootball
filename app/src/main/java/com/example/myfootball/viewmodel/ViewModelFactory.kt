package com.example.myfootball.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfootball.repository.FootballRepository
import com.example.myfootball.utils.NetworkHelper
import java.lang.Exception

class ViewModelFactory(
    private val footballRepository: FootballRepository,
    private val networkHelper: NetworkHelper
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FootballViewModel::class.java)){
            return FootballViewModel(footballRepository,networkHelper) as T
        }
        throw Exception("Error")
    }
}