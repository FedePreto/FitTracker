package com.example.fittracker.autenticazione

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fittracker.databinding.ActivityAltezzaBinding
import com.example.fittracker.databinding.ActivitySessoBinding

class AltezzaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAltezzaBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltezzaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }





}