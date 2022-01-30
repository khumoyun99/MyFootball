package com.example.myfootball.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myfootball.R
import com.example.myfootball.adapter.ViewPagerAdapter
import com.example.myfootball.databinding.FragmentItemBinding
import com.example.myfootball.models.club.FootballClub
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class ItemFragment : Fragment() {

    private lateinit var binding:FragmentItemBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var list = arrayListOf("Matches","Table")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater,container,false)

        val bundle = Bundle(arguments)
        val footballClub:FootballClub = bundle.getSerializable("football") as FootballClub
        viewPagerAdapter = ViewPagerAdapter(requireActivity(),footballClub)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,pos->
            tab.text = list[pos]
        }.attach()

        binding.backImg.setOnClickListener {
            findNavController().popBackStack()
        }

        Picasso.get().load(footballClub.logo)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.leagueFlagImg)

        return binding.root
    }


}