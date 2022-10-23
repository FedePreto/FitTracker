package com.example.fittracker.databaseFB

import android.content.Context
import android.widget.Toast
import com.example.fittracker.model.Utente
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class UtenteDB : FirebaseDB() {
    // Riferimento alla collection Utente

    val utenti_collection = db.collection("Utente")
    var status = false

    suspend fun addUtente(
        nome: String,
        cognome : String,
        email: String,
        obbiettivo: Int,
        sesso: String,
        data_nascita: String,
        altezza: Int,
        peso_attuale: Double,
        peso_obbiettivo: Double?,
        kg_settimanali: Double?,
        data_raggiungimento: String?,
        contesto: Context): Boolean {
        val utente = hashMapOf<String, Any>(
            "nome" to nome,
            "cognome" to cognome,
            "email" to email,
            "obbiettivo" to obbiettivo,
            "sesso" to sesso,
            "data_nascita" to data_nascita,
            "altezza" to altezza,
            "peso_attuale" to peso_attuale,
            "peso_obbiettivo" to peso_obbiettivo!!,
            "kg_settimanali" to kg_settimanali!!,
            "data_raggiungimento" to data_raggiungimento!!
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

    suspend fun getUtenti(): List<Utente>{
        return utenti_collection.get().await().toObjects()
    }

    suspend fun getUtente(email: String) : Utente {
        val utentiList = getUtenti()
        for(utente in utentiList) {
            if (utente.email == email) {
                return utente
            }
        }
        return Utente()

    }
}