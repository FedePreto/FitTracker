package com.example.fittracker.autenticazione

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fittracker.databinding.ActivityPesoAttualeBinding
import com.example.fittracker.databinding.ActivitySessoBinding

class PesoAttualeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPesoAttualeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesoAttualeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}