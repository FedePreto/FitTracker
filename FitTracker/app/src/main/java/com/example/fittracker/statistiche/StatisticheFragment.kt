package com.example.fittracker.statistiche

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentStatisticheBinding

class StatisticheFragment : Fragment() {

    private lateinit var viewModel: StatisticheViewModel
    private lateinit var binding: FragmentStatisticheBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_statistiche, container, false)
    }


}