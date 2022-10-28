package com.example.fittracker.databaseFB

import com.example.fittracker.model.Dieta
import com.example.fittracker.model.Pasto
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await

class DietaDB : FirebaseDB() {
    private val diete_collection = db.collection("Diete")

    suspend fun getDiete(): List<Dieta> {
        return diete_collection.get().await().toObjects()
    }

    //suspend fun getDieta(titolo: String){}
}

