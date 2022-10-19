package com.example.fittracker.io

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentIoBinding
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class IoFragment : Fragment() {

    private lateinit var viewModel: IoViewModel
    private lateinit var binding: FragmentIoBinding
    private var calorie_giornaliere = 2000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_io, container, false)
    }


}