package com.example.myfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfootball.R
import com.example.myfootball.databinding.ClubRvItemBinding
import com.example.myfootball.models.club.Standing
import com.squareup.picasso.Picasso

class ClubRvAdapter(private val list: ArrayList<Standing>):RecyclerView.Adapter<ClubRvAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val clubRvItemBinding: ClubRvItemBinding):RecyclerView.ViewHolder(clubRvItemBinding.root){
        fun onBind(standing: Standing){

            clubRvItemBinding.apply {
                if(standing.team.logos!=null){
                    Picasso.get().load(standing.team.logos[0].href)
                        .error(R.drawable.ic_launcher_background).
                        placeholder(R.drawable.ic_launcher_background).
                        into(clubFlagImg)
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
        return MyViewHolder(ClubRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return 4
    }
}