package com.example.fittracker.databaseFB

import com.example.fittracker.model.Pasto
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class PersonalizzatiDB : FirebaseDB() {
    val personalizzati_collection = db.collection("Personalizzati")
    var status = false

    suspend fun setPastoPersonalizzati(
        utente : String,
        tipologiaPasto : String,
        nome/*label*/ : String,
        calorie : Int,
        proteine : Int,
        carboidrati : Int,
        grassi : Int
    ): Boolean {
        val id = UUID.randomUUID().toString()
        val prodotto = hashMapOf<String, Any>(
            "id" to id,
            "nome" to nome,
            "calorie" to calorie,
            "proteine" to proteine,
            "carboidrati" to carboidrati,
            "grassi" to grassi
        )
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente)
                .collection(tipologiaPasto)
                .document(id)
                .set(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getPastiPersonalizzati(utente: String, tipologiaPasto: String): List<Pasto> {
        return personalizzati_collection
            .document(utente)
            .collection(tipologiaPasto).get().await().toObjects()

    }

    suspend fun deletePersonalizzato(utente: String,id:String,tipologiaPasto: String): Boolean{
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente).collection(tipologiaPasto)
                .document(id).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }

    suspend fun updatePastoPersonalizzato(
        id : String,
        utente : String,
        tipologiaPasto : String,
        nome/*label*/ : String,
        calorie : Int,
        proteine : Int,
        carboidrati : Int,
        grassi : Int
    ): Boolean {
        val prodotto = hashMapOf<String, Any>(
            "id" to id,
            "nome" to nome,
            "calorie" to calorie,
            "proteine" to proteine,
            "carboidrati" to carboidrati,
            "grassi" to grassi
        )
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente)
                .collection(tipologiaPasto)
                .document(id)
                .set(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }


}