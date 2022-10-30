package com.example.fittracker.esercizio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityAggiungiEsercizioBinding
import com.example.fittracker.databinding.ActivityEsercizioBinding

class AggiungiEsercizioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAggiungiEsercizioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_aggiungi_esercizio)
    }
}