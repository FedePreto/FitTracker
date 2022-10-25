package com.example.fittracker.profilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityProfiloBinding

class ProfiloActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfiloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profilo)
    }
}