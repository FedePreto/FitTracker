package com.example.fittracker.databaseFB

import com.example.fittracker.model.Pasto
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PreferitiDB : FirebaseDB() {
    val prodotti_collection = db.collection("Preferiti")
    var status = false

    suspend fun setPastoPreferiti(
        utente : String,
        tipologiaPasto : String,
        foodId : String,
        image : String,
        nome/*label*/ : String,
        calorie : Double,
        proteine : Double,
        carboidrati : Double,
        grassi : Double
    ): Boolean {
        val prodotto = hashMapOf<String, Any>(
            "id" to foodId,
            "image" to image,
            "nome" to nome,
            "calorie" to calorie,
            "proteine" to proteine,
            "carboidrati" to carboidrati,
            "grassi" to grassi
        )
        withContext(Dispatchers.IO) {
            prodotti_collection
                .document(utente)
                .collection(tipologiaPasto)
                .document(foodId)
                .set(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getPastiPreferiti(utente: String, tipologiaPasto: String): List<Pasto> {
        return prodotti_collection
            .document(utente)
            .collection(tipologiaPasto).get().await().toObjects()

    }

    suspend fun deletePreferiti(utente: String,id:String,tipologiaPasto: String): Boolean{
        withContext(Dispatchers.IO) {
            prodotti_collection
            .document(utente).collection(tipologiaPasto)
            .document(id).delete()
            .addOnSuccessListener {status = true }
            .addOnFailureListener {status = false }
        }.await()
        return status
    }
}

