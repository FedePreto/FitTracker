package com.example.fittracker.autenticazione

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fittracker.home.HomeActivity
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityObbiettivoBinding
import com.example.fittracker.model.ObbPeso
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ObbiettivoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityObbiettivoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObbiettivoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }



}