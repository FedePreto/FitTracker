package com.example.fittracker.autenticazione

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.fittracker.MainActivity
import com.example.fittracker.databinding.ActivityRegisterBinding
import com.example.fittracker.databinding.FragmentSliderBinding
import com.example.fittracker.home.HomeActivity
import com.example.fittracker.model.Utente
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
class RegisterActivity : AppCompatActivity() {
    private lateinit var utente: Utente
    private lateinit var binding: ActivityRegisterBinding
    private val model= AuthViewModel()
    val args: RegisterActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        utente = args.utente
        setStep(utente.obbiettivo)

        binding.btnRegister.setOnClickListener {
            val email = binding.InputEmail.text.toString().trim()
            val pass = binding.InputPassword.text.toString().trim()
            val confPass = binding.InputCorrectPassword.text.toString().trim()
            val check = checkFields(email, pass, confPass)

            lifecycleScope.launch {
                if (check) {
                    if (model.singUp(email, pass) == null) {
                        Toast.makeText(this@RegisterActivity,"Ops, la registrazione non è andata a buon fine",Toast.LENGTH_SHORT).show()
                    } else {


                        model.addAuthUtenteOnDB(utente.nome, utente.cognome, email, utente.obbiettivo, utente.sesso,
                            utente.data_nascita,utente.altezza,utente.peso_attuale,
                            utente.peso_obbiettivo,utente.kg_settimanali,
                            utente.data_raggiungimento, this@RegisterActivity)
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }
    }
    private fun checkFields(email: String, pass: String, confPass: String): Boolean {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.InputEmail.setError("Il formato dell'Email è errato!")
            binding.InputEmail.requestFocus()
            return false
        }

        if (pass.isEmpty()) {
            binding.InputPassword.setError("La password è richiesta")
            binding.InputPassword.requestFocus()
            return false
        }


        if (pass.length < 6) {
            binding.InputPassword.setError("La password deve essere di almeno 6 caratteri")
            binding.InputPassword.requestFocus()
            return false
        }

        if (confPass.isEmpty()) {
            binding.InputCorrectPassword.setError("Conferma la tua password per favore")
            binding.InputCorrectPassword.requestFocus()
            return false
        }

        if (!pass.equals(confPass)) {
            binding.InputCorrectPassword.setError("Le password non corrispondono!")
            binding.InputCorrectPassword.setText(" ")
            return false
        }
        else
            return true
    }

    fun setStep(obbietivo: Int) {
        when (obbietivo) {
            0 -> {
                binding.imageView69.isVisible = true
                binding.imageView68.isVisible = true
            }

            1 -> {
                binding.imageView69.isVisible = false
                binding.imageView68.isVisible = false
            }

            2 -> {
                binding.imageView69.isVisible = true
                binding.imageView68.isVisible = true
            }
        }
    }
}