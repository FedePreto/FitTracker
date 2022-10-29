package com.example.fittracker.databaseFB

import com.example.fittracker.model.Json_Parsing.Prodotto
import com.example.fittracker.model.Pasto
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProdottoDB : FirebaseDB(){
    val prodotti_collection = db.collection("Pasti")
    var status = false

    suspend fun setPasto(
        utente : String,
        date : String,
        tipologiaPasto: String,
        foodId : String,
        image : String,
        nome : String,
        calorie : Double,
        proteine : Double,
        carboidrati : Double,
        grassi: Double,
        quanita: Double
    ): Boolean {
        val prodotto = hashMapOf<String, Any>(
            "id" to foodId,
            "image" to image,
            "nome" to nome,
            "calorie" to calorie,
            "proteine" to proteine,
            "carboidrati" to carboidrati,
            "grassi" to grassi,
            "quantita" to quanita
        )
        withContext(Dispatchers.IO) {
            prodotti_collection
                .document(date+'_'+utente)
                .collection(tipologiaPasto)
                .document(foodId)
                .set(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getProdotti(date : String, utente : String, tipologiaPasto: String): List<Pasto>?{
        try{
            return prodotti_collection
                .document(date+'_'+utente)
                .collection(tipologiaPasto).get().await().toObjects()
        }catch (e: Exception){
            return null
        }
    }

    suspend fun updatePasto(
        utente : String,
        date : String,
        tipologiaPasto: String,
        foodId : String,
        quanita: Double
    ): Boolean {
        val prodotto = hashMapOf<String, Any>(
            "quantita" to quanita
        )
        withContext(Dispatchers.IO) {
            prodotti_collection
                .document(date+'_'+utente)
                .collection(tipologiaPasto)
                .document(foodId)
                .update(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun deleteProdotti(date : String, utente : String, tipologiaPasto: String, id : String) : Boolean{
        prodotti_collection.document(date+'_'+utente)
            .collection(tipologiaPasto).document(id).delete()
            .addOnSuccessListener(){status = true}
            .addOnFailureListener { status = false}
            .await()
        return status

    }

}