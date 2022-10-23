package com.example.fittracker.diario

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fittracker.databaseFB.DiarioDB
import com.example.fittracker.model.Diario
import com.google.firebase.auth.ktx.userProfileChangeRequest

class DiarioViewModel : ViewModel() {

    private val diarioDB = DiarioDB()

    private var _diario = MutableLiveData(Diario())
    val diario: LiveData<Diario>
        get() = _diario

    var isFull = arrayOf(false,false,false,false,false,false,false,false)

/*
    suspend fun addDiarioOnDB(nome:String, cognome:String, email:String, obbiettivo:Int,
                                  sesso:String, data_nascita:String, altezza:Int, peso_attuale:Double,
                                  peso_obbiettivo:Double?, kg_settimanali:Double?, data_raggiungimento:String?,
                                  contesto: Context
    ) {
        try {
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = nome + ' ' + cognome
            }
            user!!.updateProfile(profileUpdates)
            utenteDB.addUtente(nome, cognome, email, obbiettivo,sesso, data_nascita, altezza,
                peso_attuale, peso_obbiettivo, kg_settimanali, data_raggiungimento, contesto)

        } catch (e: Exception) {
        }
    }

 */



}