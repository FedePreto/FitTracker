package com.example.fittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.fittracker.databinding.ActivityRegisterBinding
import com.example.fittracker.model.User
import com.google.firebase.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var database = FirebaseDatabase.getInstance().getReference("Users")
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.HoGiaAccount.setOnClickListener(){
            //startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.btnRegister.setOnClickListener{ registrationFunction()}


    }
    private fun registrationFunction(){
        val Username = binding.InputUsername.text.toString().trim()
        val Name = binding.InputName.text.toString().trim()
        val Lastname = binding.InputLastname.text.toString().trim()
        val Email = binding.InputEmail.text.toString()
        val Pass = binding.InputPassword.text.toString()
        val ConfPass = binding.InputCorrectPassword.text.toString().trim()
        val check = checkFields(Username, Name, Lastname, Email, Pass, ConfPass)
        auth = Firebase.auth

        if (check == true) {
            val user = User(Username, Name, Lastname, Email, Pass)
            auth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val firebaseUser: FirebaseUser = it.result!!.user!!
                    database.child(firebaseUser.uid).setValue(user)
                    Toast.makeText(this, "Ti sei registrato con successo!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, DatiPersonaliActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this, "Qualcosa è andato storto, riprova!", Toast.LENGTH_LONG).show()

                }
            }
        }
    }
    private fun checkFields(Username: String, Name: String, Lastname: String, Email: String, Pass: String, ConfPass: String): Boolean {
        if (Username.isEmpty()) {
            binding.InputUsername.setError("Username is required")
            binding.InputUsername.requestFocus()
            return false
        }

        if (Name.isEmpty()) {
            binding.InputName.setError("Name is required")
            binding.InputName.requestFocus()
            return false
        }
        if (Lastname.isEmpty()) {
            binding.InputLastname.setError("Lastname is required")
            binding.InputLastname.requestFocus()
            return false

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            binding.InputEmail.setError("Il formato dell'Email è errato!")
            binding.InputEmail.requestFocus()
            return false
        }

        if (Pass.isEmpty()) {
            binding.InputPassword.setError("Password is required")
            binding.InputPassword.requestFocus()
            return false
        }

        if(Pass.length<6){
            binding.InputPassword.setError("Password MUST BE AT LEAST 6 CHARACTERS")
            binding.InputPassword.requestFocus()
        }

        if (ConfPass.isEmpty()) {
            binding.InputCorrectPassword.setError("Confirm your password please")
            binding.InputCorrectPassword.requestFocus()
            return false

        }

        if(!Pass.equals(ConfPass)){
            binding.InputCorrectPassword.setError("Passwords don't match!")
            binding.InputCorrectPassword.setText(" ")
            return false
        }
        else
            return true
    }

}