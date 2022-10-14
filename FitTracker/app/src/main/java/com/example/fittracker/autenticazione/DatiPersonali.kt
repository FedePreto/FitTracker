package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fittracker.databinding.ActivityDatiPersonaliBinding

class DatiPersonali : AppCompatActivity() {
    private lateinit var binding: ActivityDatiPersonaliBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatiPersonaliBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}