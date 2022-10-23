package com.example.fittracker.databaseFB

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.fittracker.model.Diario
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DiarioDB : FirebaseDB() {
    val diari_collection = db.collection("Diario")
    var status = false

    suspend fun setDiario(
        utente : String,
        data : String,
        fabbisogno : Int,
        grassiTot : Int,
        proteineTot : Int,
        carboidratiTot : Int,
        chiloCalorieEsercizio : Int,
        chiloCalorieColazione : Int,
        chiloCaloriePranzo : Int,
        chiloCalorieCena : Int,
        chiloCalorieSpuntino : Int,
        acqua : ArrayList<Boolean>
    ): Boolean {
        val diario = hashMapOf<String, Any>(
            "utente" to utente,
            "data" to data,
            "fabbisogno" to fabbisogno,
            "grassiTot" to grassiTot,
            "proteineTot" to proteineTot,
            "carboidratiTot" to carboidratiTot,
            "chiloCalorieEsercizio" to chiloCalorieEsercizio,
            "chiloCalorieColazione" to chiloCalorieColazione,
            "chiloCaloriePranzo" to chiloCaloriePranzo,
            "chiloCalorieCena" to chiloCalorieCena,
            "chiloCalorieSpuntino" to chiloCalorieSpuntino,
            "acqua" to acqua
        )
        withContext(Dispatchers.IO) {
            diari_collection
                .document(data+'_'+utente)
                .set(diario)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getDiari(): List<Diario>?{
        try{
            return diari_collection.get().await().toObjects()
        }catch (e: Exception){
            return null
        }
    }

    suspend fun getUserDiario(utente: String) : Diario? {
        val diariList = getDiari()
        val date = LocalDate.now().toString()
        if(diariList != null){
            for(diario in diariList) {
                if (diario.utente == utente && diario.data == date)
                    return diario
            }
        }
        return null
    }
}