package com.example.myfootball.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myfootball.R
import com.example.myfootball.adapter.StandingsRvAdapter
import com.example.myfootball.database.AppDatabase
import com.example.myfootball.databinding.FragmentHomeBinding
import com.example.myfootball.models.club.FootballClub
import com.example.myfootball.repository.FootballRepository
import com.example.myfootball.retrofit.ApiClient
import com.example.myfootball.utils.ItemFootballClubResource
import com.example.myfootball.utils.NetworkHelper
import com.example.myfootball.viewmodel.FootballViewModel
import com.example.myfootball.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var footballViewModel: FootballViewModel
    private lateinit var appDatabase: AppDatabase
    private lateinit var networkHelper: NetworkHelper
    private lateinit var footballRepository: FootballRepository
    private lateinit var standingsRvAdapter:StandingsRvAdapter
    private lateinit var footballClubList:ArrayList<FootballClub>

    private val TAG = "HomeFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: onCreateView")
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        appDatabase = AppDatabase.getInstance(requireContext())
        networkHelper = NetworkHelper(requireContext())
        footballRepository = FootballRepository(ApiClient.apiService,appDatabase.leagueDao())

        footballViewModel = ViewModelProvider(
            this,
            ViewModelFactory(footballRepository, networkHelper),
        )[FootballViewModel::class.java]


        footballClubList = ArrayList()
        lifecycleScope.launch{
            footballViewModel.fetchFootball()
                .collect {
                    when(it){
                        is ItemFootballClubResource.Loading -> {
                            Log.d(TAG, "onCreateView: Loading")
                            binding.progressBarRl.visibility = View.VISIBLE
                            binding.rv.visibility = View.GONE

                        }
                        is ItemFootballClubResource.Error -> {
                            Log.d(TAG, "onCreateView: ${it.message}")
                            binding.rv.visibility = View.GONE
                            binding.progressBarRl.visibility = View.VISIBLE

                        }
                        is ItemFootballClubResource.Success -> {
                            binding.progressBarRl.visibility = View.GONE
                            binding.rv.visibility = View.VISIBLE

                            footballClubList = ArrayList(it.list)
                            Log.d(TAG, "onCreateView: ${it.list.size}")

                            standingsRvAdapter = StandingsRvAdapter(footballClubList,object :StandingsRvAdapter.OnItemClickListener{
                                override fun onItemClick(footballClub: FootballClub) {
                                    val bundle = Bundle()
                                    bundle.putSerializable("football",footballClub)
                                    findNavController().navigate(R.id.itemFragment,bundle)
                                }
                            })
                            binding.rv.adapter = standingsRvAdapter
                        }
                    }


                }
        }




        return binding.root
    }



}