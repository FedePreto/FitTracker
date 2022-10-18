package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityConosciamociBinding
import com.example.fittracker.databinding.ActivityInizioBinding

class ConosciamociActivity : AppCompatActivity(){
    private lateinit var binding: ActivityConosciamociBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConosciamociBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NavHost.getFragment<ObbiettivoFragment>()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavHost)
        val navController = Navigation.findNavController(this, R.id.NavHost)

    }




}