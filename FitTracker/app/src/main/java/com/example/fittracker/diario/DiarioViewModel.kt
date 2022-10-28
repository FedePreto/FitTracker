package com.example.fittracker.diario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fittracker.databaseFB.DiarioDB
import com.example.fittracker.databaseFB.ProdottoDB
import com.example.fittracker.databaseFB.UtenteDB
import com.example.fittracker.model.Diario
import com.example.fittracker.model.Pasto
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

class DiarioViewModel : ViewModel() {

    private val diarioDB = DiarioDB()
    private val utenteDB = UtenteDB()
    private val auth = FirebaseAuth.getInstance()

    private var _diario = MutableLiveData<Diario>()
    val diario: LiveData<Diario>
        get() = _diario

    private var _acqua = MutableLiveData<String>()

    val acqua : LiveData<String>
        get() = _acqua

    private var _assunte = MutableLiveData<String>()
    val assunte : LiveData<String>
        get() = _assunte

    private var _rimanenti = MutableLiveData<String>()
    val rimanenti : LiveData<String>
        get() = _rimanenti

    val result : LiveData<String> = Transformations.map(rimanenti){
        x -> if (x.toInt() < 0) "Nessuna" else x
    }

    fun setDiarioOnDB(grassiTot:Int = 0, proteineTot:Int = 0,
                      carboidratiTot:Int = 0, chiloCalorieEsercizio:Int = 0, chiloCalorieColazione:Int = 0,
                      chiloCaloriePranzo:Int = 0, chiloCalorieCena:Int = 0, chiloCalorieSpuntino:Int = 0,
                      acqua: ArrayList<Boolean> = arrayListOf(false, false, false, false, false, false, false, false)){
        viewModelScope.launch {
            val utente = utenteDB.getUtente(auth.currentUser?.email!!)
            val today = LocalDate.now()
            val birthday: LocalDate = LocalDate.parse(utente.data_nascita)
            val period: Period = Period.between(birthday, today)
            var fabbisogno = 0.0
            if(utente.sesso == "uomo")
                fabbisogno = (66 + (13.7 * utente.peso_attuale) + (5 * utente.altezza) - (6.8 * period.years)) * utente.LAF
            else
                fabbisogno = (65 + (9.6 * utente.peso_attuale) + (1.8 * utente.altezza) - (4.7 * period.years)) * utente.LAF

            diarioDB.setDiario(auth.currentUser?.email!!,LocalDate.now().toString(), fabbisogno.toInt(), grassiTot, proteineTot, carboidratiTot, chiloCalorieEsercizio,
                                chiloCalorieColazione, chiloCaloriePranzo, chiloCalorieCena, chiloCalorieSpuntino, acqua)
        }
    }

   fun getUserDiarioDB(){
       viewModelScope.launch {
           _diario.value = diarioDB.getUserDiario(auth.currentUser?.email!!)

       }

    }

    //Variabile usata per la visualizzazione, altrimenti avrei visualizzato solamente il numero
    fun setAcqua(acqua : Double){
        var water = acqua.toString() + " L"
        _acqua.value = water
    }

    fun setAssunte(){
        val assunte = diario.value!!.chiloCalorieCena + diario.value!!.chiloCalorieColazione + diario.value!!.chiloCaloriePranzo + diario.value!!.chiloCalorieSpuntino
        _assunte.value = assunte.toString()
    }

    fun setRimanenti(){
        val rimanenti = diario.value!!.fabbisogno - assunte.value!!.toInt()
        _rimanenti.value = rimanenti.toString()

    }

}
