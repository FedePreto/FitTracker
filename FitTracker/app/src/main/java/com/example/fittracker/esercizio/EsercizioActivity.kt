package com.example.fittracker.esercizio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityEsercizioBinding
import com.example.fittracker.databinding.ActivityProdottoBinding

class EsercizioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEsercizioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_esercizio)

    }
}