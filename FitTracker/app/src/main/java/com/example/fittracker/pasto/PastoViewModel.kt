package com.example.fittracker.pasto

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.ProdottoDB
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

import java.time.LocalDate

class PastoViewModel : ViewModel() {

    private val prodottoDB = ProdottoDB()
    private val auth = FirebaseAuth.getInstance()

    fun deletePasto(tipologiaPasto : String, id : String, context : Context){
        viewModelScope.launch {
            if(prodottoDB.deleteProdotti(LocalDate.now().toString(),auth.currentUser!!.email!!, tipologiaPasto,id))
                Toast.makeText(context,"Prodotto eliminato con successo",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"ATTENZIONE, eliminazione prodotto fallita",Toast.LENGTH_LONG).show()
        }
    }


}