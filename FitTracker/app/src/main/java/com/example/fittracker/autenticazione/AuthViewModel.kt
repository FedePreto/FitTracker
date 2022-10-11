package com.example.fittracker.autenticazione

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.fittracker.databaseFB.UtenteDB
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val UtenteDB = UtenteDB()







    suspend fun singUp(email: String, password: String): FirebaseUser? {
        return try {
            val response = auth.createUserWithEmailAndPassword(email, password).await()
            response.user
        } catch (e: Exception){
            null
        }
    }

    suspend fun signIn(email: String, password: String): FirebaseUser? {
        return try {
            val signin = auth.signInWithEmailAndPassword(email, password).await()
            signin.user
        } catch (e: Exception) {
            null
        }
    }


    fun logOut() {
        auth.signOut()
    }


    suspend fun addAuthUtenteOnDB(email: String, contesto: Context) {
        try {
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = email
            }
            user!!.updateProfile(profileUpdates)
            UtenteDB.addUtente(email, contesto)

        } catch (e: Exception) {
        }
    }




}
