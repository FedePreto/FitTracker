package com.example.fittracker.autenticazione

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.time.LocalDate
import android.widget.Toast
import com.example.fittracker.databinding.ActivityDatiPersonaliBinding
import com.example.fittracker.model.DatiPersonali
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore


class DatiPersonaliActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatiPersonaliBinding
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatiPersonaliBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnConferma.setOnClickListener{
            confermaDatiFunction()
        }
        onBackPressed()
        db = Firebase.firestore

    }

    fun confermaDatiFunction() {
        val Eta = binding.eta.text.toString()
        val Altezza = binding.altezza.text.toString()
        val Peso = binding.peso.text.toString()
        val Torace = binding.petto.text.toString()
        val Bicipite = binding.braccio.text.toString()
        val Quadricipite = binding.gamba.text.toString()
        val Fianchi = binding.fianchi.text.toString()
        val check = checkFields(Eta, Altezza, Peso, Torace, Bicipite, Quadricipite, Fianchi)
        val datiUser = DatiPersonali(Eta, Altezza, Peso, Torace, Bicipite, Quadricipite, Fianchi)


        val data = LocalDate.now()
        val currentUser = Firebase.auth.currentUser
        val uid = currentUser?.uid.toString()
        if (check) {
            db.collection("DatiUtente").document("$uid").collection("StoricoMisurazioni").document("$data").set(datiUser)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Salvato con successo!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, ObbiettivoActivity::class.java))
                        finish()
                    } else {

                        Toast.makeText(this, "Qualcosa è andato storto!", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    override fun onBackPressed() {
    }

    private fun checkFields(Eta : String,Altezza:String,Peso:String,Torace:String,Bicipite:String,Quadricipite:String,Fianchi:String): Boolean {
        if (Eta.isEmpty()){
            binding.eta.setError("L'età è obbligatoria!")
            binding.eta.requestFocus()
            return false
            }
        if (Altezza.isEmpty()){
            binding.altezza.setError("L'altezza è obbligatoria!")
            binding.altezza.requestFocus()
            return false
            }
        if (Peso.isEmpty()){
            binding.peso.setError("Il peso è obbligatorio!")
            binding.peso.requestFocus()
            return false
        }
        if (Torace.isEmpty()){
            binding.petto.setError("La misurazione del torace è obbligatoria, se non la si conosce si può inserire 0 e cambiarla più tardi!")
            binding.petto.requestFocus()
            return false
        }
        if (Bicipite.isEmpty()){
            binding.braccio.setError("La misurazione del braccio è obbligatoria, se non la si conosce si può inserire 0 e cambiarla più tardi!")
            binding.braccio.requestFocus()
            return false
        }
        if (Quadricipite.isEmpty()){
            binding.gamba.setError("La misurazione della gamba è obbligatoria, se non la si conosce si può inserire 0 e cambiarla più tardi!")
            binding.gamba.requestFocus()
            return false
        }
        if (Fianchi.isEmpty()){
            binding.fianchi.setError("La misurazione dei fianchi è obbligatoria, se non la si conosce si può inserire 0 e cambiarla più tardi!")
            binding.fianchi.requestFocus()
            return false
        }
        if (Eta.toShort()<=13){
            binding.eta.setError("L'età consigliata per l'utilizzo è di almeno 14 anni!")
            binding.eta.requestFocus()
            return false
        }
        if (Altezza.toShort() in 301..129){
            binding.altezza.setError("Altezza non valida! (min. 130 cm - max 300 cm)")
            binding.altezza.requestFocus()
            return false
        }
        if (Peso.toShort()<35){
            binding.peso.setError("Peso non valido! (min. 35 kg)")
            binding.peso.requestFocus()
            return false
        }
        if (Torace.toShort()<0){
            binding.petto.setError("Misura non valida!")
            binding.petto.requestFocus()
            return false
        }
        if (Bicipite.toShort()<0){
            binding.braccio.setError("Misura non valida!")
            binding.braccio.requestFocus()
            return false
        }
        if (Quadricipite.toShort()<0){
            binding.gamba.setError("Misura non valida!")
            binding.gamba.requestFocus()
            return false
        }
        if (Fianchi.toShort()<0){
            binding.fianchi.setError("Misura non valida!")
            binding.fianchi.requestFocus()
            return false
        }

        else
            return true
    }

}





