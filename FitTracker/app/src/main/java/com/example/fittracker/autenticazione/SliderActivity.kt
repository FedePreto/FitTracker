package com.example.fittracker.autenticazione

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fittracker.databinding.ActivityDatiPersonaliBinding
import com.example.fittracker.databinding.ActivitySliderBinding

class SliderActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySliderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}