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
    private lateinit var db : FirebaseFirestore
    private var selezione: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObbiettivoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnObbiettivo.setOnClickListener{
            confermaObbiettivo()
        }
        binding.GruppoRadio.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rB_Diminuisci){
                selezione = "diminuisci"
            }
            if(checkedId == R.id.rB_Mantieni){
                selezione = "mantieni"
            }
            if(checkedId == R.id.rB_Aumenta){
                selezione = "aumenta"
            }
        }
        db = Firebase.firestore

    }

    private fun confermaObbiettivo(){
        val rb_Diminuisci = binding.rBDiminuisci
        val rb_Mantieni = binding.rBMantieni
        val rb_Aumenta = binding.rBAumenta

        val check = checkFields(rb_Aumenta, rb_Diminuisci, rb_Mantieni)
        val currentUser = Firebase.auth.currentUser
        val uid = currentUser?.uid.toString()
        val obbUser = ObbPeso(selezione)

        if(check){
            db.collection("DatiUtente").document("$uid").collection("ObbiettivoPeso").document("ObbiettivoIniziale").set(obbUser)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Obbiettivo salvato con successo!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {

                        Toast.makeText(this, "Qualcosa è andato storto!", Toast.LENGTH_LONG).show()
                    }
                }


        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, InizioActivity::class.java))
        finish()
    }


    private fun checkFields( rb_Aumenta : RadioButton, rb_Diminuisci : RadioButton, rb_Mantieni: RadioButton) : Boolean{
        if(!rb_Aumenta.isChecked && !rb_Diminuisci.isChecked && !rb_Mantieni.isChecked){
            binding.rBMantieni.setError("Selezionare una tipologia di obbiettivo!")
            binding.GruppoRadio.requestFocus()
            return false
        }

        else
            return true

    }



}