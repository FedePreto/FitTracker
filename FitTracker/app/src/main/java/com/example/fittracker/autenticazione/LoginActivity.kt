package com.example.fittracker.autenticazione

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.fittracker.home.HomeActivity
import com.example.fittracker.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val model = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val email = binding.InputEmailLogin.text.toString()
            val password = binding.InputPasswordLogin.text.toString()

            if(model.checkUtenteisLoggato()){
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    if (model.signIn(email, password) == null) {
                        MaterialAlertDialogBuilder(this@LoginActivity)
                            .setTitle("Attenzione!")
                            .setMessage("Ops, qualcosa è andato storto...")
                            .setPositiveButton("OK") { dialog, which ->
                                dialog.dismiss()
                            }
                            .show()
                    } else {
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }

        //Controllo se l'utente è già loggato

/*
        if(auth.currentUser != null) {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.forgotPass.setOnClickListener {
            val intent = Intent(this@LoginActivity, RecuperoPasswordActivity::class.java)
            startActivity(intent)
        }


    }



        binding.btnLogin.setOnClickListener { loginFunction() }
        // KEEP USER LOGG

        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity()
            finish()
        }



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
