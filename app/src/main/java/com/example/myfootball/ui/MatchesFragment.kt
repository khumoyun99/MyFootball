package com.example.myfootball.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfootball.R
import com.example.myfootball.databinding.FragmentMatchesBinding
import com.example.myfootball.models.club.FootballClub

private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class MatchesFragment : Fragment() {

    private var param1: FootballClub? = null
//    private var param2: String? = null
    private lateinit var binding: FragmentMatchesBinding

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
        binding = FragmentMatchesBinding.inflate(inflater,container,false)


        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: FootballClub) =
            MatchesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}