package com.example.fittracker.autenticazione

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.fittracker.databinding.ActivityRegisterBinding
import com.example.fittracker.home.HomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val model= AuthViewModel()

    /*private var database = FirebaseDatabase.getInstance().getReference("Users")
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.HoGiaAccount.setOnClickListener() {
            finish()
        }
        binding.btnRegister.setOnClickListener { registrationFunction() }


    }

    private fun registrationFunction() {
        val Username = binding.InputUsername.text.toString().trim()
        val Name = binding.InputName.text.toString().trim()
        val Lastname = binding.InputLastname.text.toString().trim()
        val Email = binding.InputEmail.text.toString()
        val Pass = binding.InputPassword.text.toString()
        val ConfPass = binding.InputCorrectPassword.text.toString().trim()
        //val check = checkFields(Username, Name, Lastname, Email, Pass, ConfPass)


        lifecycleScope.launch {
            if (model.singUp(Email, Pass) == null) {
                MaterialAlertDialogBuilder(this@RegisterActivity)
                    .setTitle("Attenzione!")
                    .setMessage("Riempi tutti i campi richiesti per continuare")
                    .setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            } else {
                model.addAuthUtenteOnDB(Username, Name, Lastname, Email, this@RegisterActivity)
                val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

        /*if (check) {
            val user = User(Username, Name, Lastname,Email)
            auth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val currentUser = Firebase.auth.currentUser
                    val uid = currentUser?.uid.toString()
                    val firebaseUser: FirebaseUser = it.result!!.user!!
                    database.child(firebaseUser.uid).setValue(user)
                    db.collection("DatiUtente").document("$uid").collection("Profilo").document("Anagrafiche").set(user)
                        .addOnCompleteListener(this) {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Ti sei registrato con successo!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this, DatiPersonaliActivity::class.java))
                                finish()
                            } else {

                                Toast.makeText(this, "Qualcosa è andato storto!", Toast.LENGTH_LONG).show()
                            }
                        }

                } else {
                    Toast.makeText(this, "Qualcosa è andato storto, riprova!", Toast.LENGTH_LONG).show()

                }
            }
        }
    }

    private fun checkFields(
        Username: String,
        Name: String,
        Lastname: String,
        Email: String,
        Pass: String,
        ConfPass: String
    ): Boolean {
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

        if (Pass.length < 6) {
            binding.InputPassword.setError("Password MUST BE AT LEAST 6 CHARACTERS")
            binding.InputPassword.requestFocus()
        }

        if (ConfPass.isEmpty()) {
            binding.InputCorrectPassword.setError("Confirm your password please")
            binding.InputCorrectPassword.requestFocus()
            return false

        }

        if (!Pass.equals(ConfPass)) {
            binding.InputCorrectPassword.setError("Passwords don't match!")
            binding.InputCorrectPassword.setText(" ")
            return false
        } else
            return true
    }
*/

}