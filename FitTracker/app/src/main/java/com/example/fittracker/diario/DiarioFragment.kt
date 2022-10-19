package com.example.fittracker.diario

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentDiarioBinding
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class DiarioFragment : Fragment() {
    private lateinit var binding: FragmentDiarioBinding
    private lateinit var viewModel: DiarioViewModel
    private var calorie_giornaliere = 2000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.progressCalorie.apply {
            progressMax = calorie_giornaliere.toFloat()
            setProgressWithAnimation(65f, 1500)
            progressBarWidth = 7f
            backgroundProgressBarWidth = 5f
            backgroundProgressBarColor = Color.WHITE
            progressBarColor = Color.GREEN
            roundBorder = true
            startAngle= 90f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT

        }
        return inflater.inflate(R.layout.fragment_diario, container, false)
    }


}