package com.example.fittracker.autenticazione

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.fittracker.R
import com.example.fittracker.databinding.*

class ConosciamociActivity : AppCompatActivity(){
    private lateinit var binding: ActivityConosciamociBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConosciamociBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val Nav_Controller = this.findNavController(R.id.NavHost)
        Nav_Controller.navigate(R.id.obbiettivoFragment)

    }




}