package com.example.fittracker.autenticazione

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fittracker.databinding.ActivityPesoAttualeBinding
import com.example.fittracker.databinding.ActivityPesoObbBinding

class PesoObbActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPesoObbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesoObbBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}