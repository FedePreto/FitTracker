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
import com.example.fittracker.databinding.*

class ConosciamociActivity : AppCompatActivity(){
    private lateinit var binding: ActivityConosciamociBinding
    private enum class selezione{
        DIMINUISCI, MANTIENI, AUMENTA
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConosciamociBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonEffect(binding.btAvanti)
        binding.liniette.isVisible = false






    }






        @SuppressLint("ClickableViewAccessibility")
        fun buttonEffect(button: View) = button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.background.setColorFilter(0x66660000, PorterDuff.Mode.SRC_ATOP)
                    v.invalidate()
                }

                MotionEvent.ACTION_UP -> {
                    v.background.clearColorFilter()
                    v.invalidate()
                }
            }
            false
        }



}