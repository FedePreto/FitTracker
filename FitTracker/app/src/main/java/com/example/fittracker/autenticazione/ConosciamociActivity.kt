package com.example.fittracker.autenticazione

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityConosciamociBinding

class ConosciamociActivity : AppCompatActivity(){
    private lateinit var binding: ActivityConosciamociBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConosciamociBinding.inflate(layoutInflater)
        setContentView(binding.root)
       //val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavHost)
        //val navController = Navigation.findNavController(this, R.id.NavHost)

    }




}