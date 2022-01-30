package com.example.myfootball.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfootball.R
import com.example.myfootball.adapter.ClubInfoRvAdapter
import com.example.myfootball.databinding.FragmentTableBinding
import com.example.myfootball.models.club.FootballClub
import com.example.myfootball.models.club.Standing

private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class TableFragment : Fragment() {

    private var param1: FootballClub? = null
//    private var param2: String? = null
    private lateinit var binding: FragmentTableBinding
    private lateinit var clubInfoRvAdapter: ClubInfoRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as FootballClub?
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTableBinding.inflate(inflater,container,false)
        clubInfoRvAdapter = ClubInfoRvAdapter(param1?.data?.standings as ArrayList<Standing>)
        binding.clubInfoRv.adapter = clubInfoRvAdapter
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: FootballClub) =
            TableFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}