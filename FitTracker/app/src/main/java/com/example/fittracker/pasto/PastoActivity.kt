package com.example.fittracker.pasto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityPastoBinding

class PastoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPastoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pasto)
        setContentView(binding.root)
    }
}