package com.example.fittracker.autenticazione

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.UtenteDB
import com.example.fittracker.model.Utente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.launch
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


    suspend fun addAuthUtenteOnDB(nome:String, cognome:String, email:String, obbiettivo:Int,
                                  sesso:String, data_nascita:String, altezza:Int, peso_attuale:Double,
                                  peso_obbiettivo:Double?, kg_settimanali:Double?, data_raggiungimento:String?,
                                  contesto: Context) {
        try {
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = nome + ' ' + cognome
            }
            Toast.makeText(contesto,profileUpdates.toString(),Toast.LENGTH_SHORT).show()
            user!!.updateProfile(profileUpdates)
            utenteDB.addUtente(nome, cognome, email, obbiettivo,sesso, data_nascita, altezza,
                                peso_attuale, peso_obbiettivo, kg_settimanali, data_raggiungimento, contesto)

        } catch (e: Exception) {
        }
    }

    fun getUtente(email: String){
        viewModelScope.launch {
            _utente.value = utenteDB.getUtente(email)
        }
    }

    fun checkUtenteisLoggato() : Boolean{
        if(auth.currentUser != null)
            return true
        return false
    }
}
