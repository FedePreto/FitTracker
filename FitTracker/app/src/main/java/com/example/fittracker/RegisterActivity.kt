package com.example.fittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fittracker.databinding.ActivityRegisterBinding
import com.google.firebase.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var database = FirebaseDatabase.getInstance().getReference("Users")
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener{ registrationFunction()}


    }
    private fun registrationFunction(){
        val Username = binding.InputUsername.text.toString().trim()
        val Name = binding.InputName.text.toString().trim()
        val Lastname = binding.InputLastname.text.toString().trim()
        val Email = binding.InputEmail.text.toString()
        val Pass = binding.InputPassword.text.toString()
        val ConfPass = binding.InputCorrectPassword.text.toString().trim()

        auth = Firebase.auth

        if (check == true) {
            val user = User(textName, textSurname, textConPassword, textdateOfBirth, textState, description)
            auth.createUserWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val firebaseUser: FirebaseUser = it.result!!.user!!
                    database.child(firebaseUser.uid).setValue(
                        user)
                    Toast.makeText(this, "You've been succesfully registred!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this, "sorry", Toast.LENGTH_LONG).show()

                }
            }
        }
    }
}