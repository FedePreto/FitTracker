package com.example.fittracker.profilo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.UtenteDB
import com.example.fittracker.model.Utente
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class ProfiloViewModel : ViewModel() {

    private val utenteDB = UtenteDB()

    private var _profilo = MutableLiveData<Utente>()

    val profilo : LiveData<Utente>
        get() = _profilo

    private val auth = FirebaseAuth.getInstance()



    fun getDataProfilo(){
        viewModelScope.launch {
           _profilo.value =  utenteDB.getUtente(auth.currentUser!!.email!!)
        }




    }
}