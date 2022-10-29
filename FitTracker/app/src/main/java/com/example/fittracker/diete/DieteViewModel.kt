package com.example.fittracker.diete

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.DietaDB
import com.example.fittracker.databaseFB.UtenteDB
import com.example.fittracker.model.Dieta
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class DieteViewModel : ViewModel() {
    private val dieteDB = DietaDB()
    private val utenteDB = UtenteDB()
    private val user = FirebaseAuth.getInstance().currentUser


    private var _dieteLiveData = MutableLiveData<List<Dieta>>()
    val dieteLiveData : LiveData<List<Dieta>>
        get() = _dieteLiveData

    private var _indiceDieta = MutableLiveData<Int>()
    val indiceDieta : LiveData<Int>
        get() = _indiceDieta


    fun getDiete(){
        viewModelScope.launch {
            _dieteLiveData.value = dieteDB.getDiete()
        }
    }


     fun updateDieta(titolo: String, context:Context){
         viewModelScope.launch {
             if (utenteDB.updateDieta(titolo, user!!.email.toString())) Toast.makeText(
                 context,
                 "La dieta è stata cambiata con successo!",
                 Toast.LENGTH_LONG
             ).show()
             else Toast.makeText(context, "Qualcosa è andato storto, riprovare.", Toast.LENGTH_LONG).show()
         }
    }

    fun setDietaView(){
        viewModelScope.launch {
            val utente = utenteDB.getUtente(user!!.email.toString())
            for (i in 0.._dieteLiveData.value!!.size){
                if(_dieteLiveData.value!![i].titolo == utente.dieta)
                    _indiceDieta.value = i
            }
        }

    }



}