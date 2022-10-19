package com.example.fittracker.databaseFB

import com.google.firebase.firestore.FirebaseFirestore

/*
*
* Ottengo l'instanza del databse firebase firestore
*
*/


open class FirebaseDB {
   protected var db = FirebaseFirestore.getInstance()
}