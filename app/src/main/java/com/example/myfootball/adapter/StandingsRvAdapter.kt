package com.example.myfootball.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfootball.R
import com.example.myfootball.databinding.RvItemBinding
import com.example.myfootball.models.club.FootballClub
import com.example.myfootball.models.club.Standing
import com.squareup.picasso.Picasso

class StandingsRvAdapter(val list:ArrayList<FootballClub>,val listener:OnItemClickListener):RecyclerView.Adapter<StandingsRvAdapter.MyViewHolder>() {

    private lateinit var clubRvAdapter: ClubRvAdapter

    inner class MyViewHolder(private val rvItemBinding: RvItemBinding):RecyclerView.ViewHolder(rvItemBinding.root){
        fun onBind(footballClub: FootballClub){
            rvItemBinding.apply {
                if(footballClub.data.abbreviation=="A Lge"){
                    countryFlagImg.setBackgroundColor(Color.WHITE)
                }
                Picasso.get().load(footballClub.logo).into(countryFlagImg)
                leagueNameTv.text = footballClub.data.name.substring(footballClub.data.name.indexOf(" ")+1)
                countryTv.text = footballClub.data.name.substring(0,footballClub.data.name.indexOf(" "))
                clubRvAdapter = ClubRvAdapter(footballClub.data.standings as ArrayList<Standing>)
                clubRv.adapter = clubRvAdapter
            }

            itemView.setOnClickListener {
                listener.onItemClick(footballClub)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener{
        fun onItemClick(footballClub: FootballClub)

    }
}