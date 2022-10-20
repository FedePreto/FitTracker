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
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val model = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(model.checkUtenteisLoggato()){
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch {
                val email = binding.InputEmailLogin.text.toString()
                val password = binding.InputPasswordLogin.text.toString()


                if (checkFields(email, password)) {
                    lifecycleScope.launch {
                        if (model.signIn(email, password) == null) {
                            binding.InputEmailLogin.setError("Email o Password errate")
                            binding.InputPasswordLogin.setError("Email o Password errate")
                        } else {
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }

            }
        }

        binding.forgotPass.setOnClickListener {
            val intent = Intent(this@LoginActivity, RecuperoPasswordActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkFields(email : String , password: String) : Boolean{
        if(email.isEmpty()){
            binding.InputEmailLogin.setError("Per favore completa il campo per effettuare l'accesso")
            binding.InputEmailLogin.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.InputEmailLogin.setError("Per favore inserisci un formato email valido")
            binding.InputEmailLogin.requestFocus()
            return false
        }

        if(password.isEmpty()){
            binding.InputPasswordLogin.setError("Per favore completa il campo per effettuare l'accesso")
            binding.InputPasswordLogin.requestFocus()
            return false
        }

        return true

    }

}
