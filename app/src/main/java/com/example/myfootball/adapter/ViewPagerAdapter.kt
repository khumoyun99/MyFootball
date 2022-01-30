package com.example.myfootball.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myfootball.models.club.FootballClub
import com.example.myfootball.ui.MatchesFragment
import com.example.myfootball.ui.TableFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity,val footballClub: FootballClub)
    :FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                MatchesFragment.newInstance(footballClub)
            }
            else->{
                TableFragment.newInstance(footballClub)
            }
        }
    }
}