package com.example.fittracker.autenticazione

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityDatiPersonaliBinding
import com.example.fittracker.databinding.ActivityObbiettivoBinding

class DatiPersonali : AppCompatActivity() {
    private lateinit var binding: ActivityDatiPersonaliBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatiPersonaliBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



}