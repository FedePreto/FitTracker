package com.example.fittracker.autenticazione

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fittracker.databaseFB.UtenteDB
import com.example.fittracker.model.Utente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val utenteDB = UtenteDB()
    private var _utente = MutableLiveData(Utente())
    val utente: LiveData<Utente>
        get() = _utente

    suspend fun singUp(email: String, password: String): FirebaseUser? {
        return try {
            val response = auth.createUserWithEmailAndPassword(email, password).await()
            response.user
        } catch (e: Exception) {
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
            //UtenteDB.addUtente(email, contesto)

        } catch (e: Exception) {
        }
    }


}
