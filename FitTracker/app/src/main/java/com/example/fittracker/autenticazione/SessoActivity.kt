package com.example.fittracker.autenticazione

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fittracker.databinding.ActivityObbiettivoBinding
import com.example.fittracker.databinding.ActivitySessoBinding

class SessoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySessoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySessoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }




}