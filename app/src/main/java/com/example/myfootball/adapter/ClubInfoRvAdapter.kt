package com.example.myfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfootball.R
import com.example.myfootball.databinding.ClubInfoItemBinding
import com.example.myfootball.models.club.FootballClub
import com.example.myfootball.models.club.Standing
import com.squareup.picasso.Picasso

class ClubInfoRvAdapter(val list:ArrayList<Standing>):RecyclerView.Adapter<ClubInfoRvAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val clubInfoItemBinding: ClubInfoItemBinding):RecyclerView.ViewHolder(clubInfoItemBinding.root){

        fun onBind(standing: Standing,position: Int){
            clubInfoItemBinding.apply {
                countTv.text = (position+1).toString()
                if(standing.team.logos!=null){
                    Picasso.get().load(standing.team.logos[0].href)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(clubFlagImg)
                }

                clubNameTv.text = standing.team.name
                standing.stats.forEach {
                    when(it.abbreviation){
                        "W"->{
                            wins.text = it.value.toString()
                        }
                        "L"->{
                            losses.text = it.value.toString()
                        }
                        "D"->{
                            draw.text = it.value.toString()
                        }
                        "GP"->{
                            gamePlayed.text = it.value.toString()
                        }
                        "P"->{
                            pts.text = it.value.toString()
                        }
                    }
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ClubInfoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size

    }
}