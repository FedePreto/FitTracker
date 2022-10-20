package com.example.fittracker.autenticazione

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.fittracker.home.HomeActivity
import com.example.fittracker.databinding.ActivityLoginBinding
import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase



class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private var database = FirebaseDatabase.getInstance().getReference("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //remeber that we are gonna initializa biding before settinf the content view
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


/*
        binding.forgotPass.setOnClickListener() {
            startActivity(Intent(this, RecuperoPasswordActivity::class.java))
        }
        binding.btnLogin.setOnClickListener { loginFunction() }
        // KEEP USER LOGG
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        */

    }
/*
    private fun loginFunction() {
        val email = binding.InputEmailLogin.text.toString()
        val password = binding.InputPasswordLogin.text.toString().trim()
        val check = checkFields(email, password)

        auth = FirebaseAuth.getInstance()

        if (check == true) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    val userReference = database?.child(user?.uid!!)
                    //updateUI(user)
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Non sei ancora registrato o email/password non sono corretti!",
                        Toast.LENGTH_LONG
                    ).show()
                    //updateUI(null)
                }
            }
        }
    }

    private fun checkFields(textEmailInfo: String, pass: String): Boolean {
        if (textEmailInfo.isEmpty()) {
            binding.InputEmailLogin.setError("Email field is empty")
            binding.InputEmailLogin.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(textEmailInfo).matches()) {
            binding.InputEmailLogin.setError("Email format is incorrect!")
            binding.InputEmailLogin.requestFocus()
            return false
        }

        if (pass.isEmpty()) {
            binding.InputPasswordLogin.setError("Password field is empty")
            binding.InputPasswordLogin.requestFocus()
            return false
        } else
            return true
    }
    */

}
