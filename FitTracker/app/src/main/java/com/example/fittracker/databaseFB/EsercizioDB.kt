package com.example.fittracker.databaseFB

import android.util.Log
import com.example.fittracker.model.Json_Parsing.Esercizio
import com.example.fittracker.model.Pasto
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class EsercizioDB : FirebaseDB() {
    val esercizi_collection = db.collection("Esercizi")
    var status = false

    suspend fun setEsercizio(
        utente : String,
        date : String,
        nome : String,
        calorieOra : Int,
        durata: Int
    ): Boolean {
        val esercizio = hashMapOf<String, Any>(
            "nome" to nome,
            "calorieOra" to calorieOra,
            "durata" to durata
        )
        withContext(Dispatchers.IO) {
            esercizi_collection
                .document(date+'_'+utente)
                .collection("ESERCIZIO")
                .document(nome.replace('/',','))
                .set(esercizio)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getEsercizi(date : String, utente : String): List<Esercizio>?{
        try{
            return esercizi_collection
                .document(date+'_'+utente)
                .collection("ESERCIZIO").get().await().toObjects()
        }catch (e: Exception){
            return null
        }
    }

    suspend fun updateEsercizio(
        utente : String,
        date : String,
        nome : String,
        durata: Int
    ): Boolean {
        val esercizio = hashMapOf<String, Any>(
            "durata" to durata
        )
        withContext(Dispatchers.IO) {
            esercizi_collection
                .document(date+'_'+utente)
                .collection("ESERCIZIO")
                .document(nome.replace('/',','))
                .update(esercizio)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun deleteEsercizio(date : String, utente : String, nome : String) : Boolean{
        esercizi_collection.document(date+'_'+utente)
            .collection("ESERCIZIO").document(nome.replace('/',',')).delete()
            .addOnSuccessListener{status = true}
            .addOnFailureListener{ status = false}
            .await()
        return status

    }

}

