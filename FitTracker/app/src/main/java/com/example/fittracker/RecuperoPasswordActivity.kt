package com.example.fittracker

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
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
            //startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.btnInvia.setOnClickListener { recuperoPassword()}

    }



    private fun recuperoPassword(){
        val email = binding.InputRecupero.text.toString().trim { it <= ' '}
        val check = checkFields(email)
        auth = FirebaseAuth.getInstance()
        if(check) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this, "Email inviata con successo!", Toast.LENGTH_LONG
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Non sei ancora registrato o l'email non è corretta!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    private fun checkFields(TextEmailInfo: String): Boolean {

        if(TextEmailInfo.isEmpty()) {
            binding.InputRecupero.setError("Email field is empty")
            binding.InputRecupero.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(TextEmailInfo).matches()) {
            binding.InputRecupero.setError("Inserire un'email valida!")
            binding.InputRecupero.requestFocus()
            return false
        }

        else
            return true
    }
}