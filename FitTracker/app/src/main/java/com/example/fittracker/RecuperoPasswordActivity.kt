package com.example.fittracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fittracker.databinding.ActivityRecPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RecuperoPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecPasswordBinding
    private lateinit var auth: FirebaseAuth
    private var database = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIndietro.setOnClickListener(){
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnInvia.setOnClickListener { recuperoPassword() }

    }



    private fun recuperoPassword(){


    }
}