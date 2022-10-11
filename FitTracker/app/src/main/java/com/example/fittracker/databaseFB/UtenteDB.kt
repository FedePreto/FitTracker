package com.example.fittracker.databaseFB

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class UtenteDB : FirebaseDB() {
    // Riferimento alla collection Utente

    val utenti_collection =db.collection("Utente")
    var status = false

    suspend fun addUtente(
        email: String,
        contesto: Context
    ): Boolean {
        val utente = hashMapOf<String, Any>(

            "email" to email
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO){
                utenti_collection
                    .document(email)
                    .set(utente)
                    .addOnSuccessListener {
                        GlobalScope.launch (Dispatchers.Main){
                            Toast.makeText(contesto, "Operazione completata con successo!", Toast.LENGTH_SHORT).show()
                        }
                        status = true

                    }
                    .addOnFailureListener{
                        Toast.makeText(contesto, "Qualcosa è andato storto...", Toast.LENGTH_SHORT).show()
                        status = false
                    }
                    .await()
            }
        }
        return status
    }








}