package com.example.fittracker.databaseFB

import android.content.Context
import android.widget.Toast
import com.example.fittracker.model.Diario
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DiarioDB : FirebaseDB() {
    val diari_collection = db.collection("Diario")
    var status = false

    suspend fun setDiario(
        utente : String,
        data : String,
        grassiTot : Int,
        proteineTot : Int,
        carboidratiTot : Int,
        chiloCalorieEsercizio : Int,
        chiloCalorieColazione : Int,
        chiloCaloriePranzo : Int,
        chiloCalorieCena : Int,
        chiloCalorieSpuntino : Int,
        acqua : ArrayList<Boolean>,
        contesto: Context
    ): Boolean {
        val diario = hashMapOf<String, Any>(
            "utente" to utente,
            "data" to data,
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

        GlobalScope.launch {
            withContext(Dispatchers.IO){
                diari_collection
                    .document(utente)
                    .set(diario)
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

    suspend fun getDiari(): List<Diario>{
        return diari_collection.get().await().toObjects()
    }

    suspend fun getUserDiario(utente: String) : Diario {
        val diariList = getDiari()
        for(diario in diariList) {
            if (diario.utente == utente) {
                return diario
            }
        }
        return Diario()
    }
}