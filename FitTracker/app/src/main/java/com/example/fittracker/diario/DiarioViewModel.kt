package com.example.fittracker.diario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fittracker.databaseFB.DiarioDB
import com.example.fittracker.databaseFB.DietaDB
import com.example.fittracker.databaseFB.ProdottoDB
import com.example.fittracker.databaseFB.UtenteDB
import com.example.fittracker.model.Diario
import com.example.fittracker.model.Pasto
import com.example.fittracker.model.Utente
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.collections.ArrayList

class DiarioViewModel : ViewModel() {

    private val diarioDB = DiarioDB()
    private val utenteDB = UtenteDB()
    private val dietaDB = DietaDB()
    private val prodottoDB = ProdottoDB()
    private val auth = FirebaseAuth.getInstance()

    private var _carboidratiMax = MutableLiveData<Int>()
    val carboidratiMax : LiveData<Int>
        get() = _carboidratiMax
    private var _proteineMax = MutableLiveData<Int>()
    val proteineMax : LiveData<Int>
        get() = _proteineMax
    private var _grassiMax = MutableLiveData<Int>()
    val grassiMax : LiveData<Int>
        get() = _grassiMax


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

    private var _selezionati = MutableLiveData<List<Pasto>>()
    val selezionati : LiveData<List<Pasto>>
        get() = _selezionati

    fun setDiarioOnDB(grassiTot:Int = 0, proteineTot:Int = 0,
                      carboidratiTot:Int = 0, chiloCalorieEsercizio:Int = 0, chiloCalorieColazione:Int = 0,
                      chiloCaloriePranzo:Int = 0, chiloCalorieCena:Int = 0, chiloCalorieSpuntino:Int = 0,
                      acqua: ArrayList<Boolean> = arrayListOf(false, false, false, false, false, false, false, false)){
        viewModelScope.launch {
            val utente = utenteDB.getUtente(auth.currentUser?.email!!)
            var fabbisogno = calculateFabbisogno(utente)
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

    fun setMacro(){
        viewModelScope.launch {
            val utente = utenteDB.getUtente(auth.currentUser?.email!!)
            val dieta = dietaDB.getDieta(utente.dieta)
            _carboidratiMax.value = ((diario.value!!.fabbisogno*(dieta.perc_carb.toDouble()/100.0)) / 4).toInt() //1gr di carbo = 4Kcal
            _proteineMax.value = ((diario.value!!.fabbisogno*(dieta.perc_prot.toDouble()/100.0)) / 4).toInt() //1gr di prot = 4Kcal
            _grassiMax.value = ((diario.value!!.fabbisogno*(dieta.perc_prot.toDouble()/100.0)) / 9).toInt()//1gr di grassi = 9Kcal
        }
    }


    fun getItemSelezionati(pasto: String){
        viewModelScope.launch {
            _selezionati.value = prodottoDB.getProdotti(LocalDate.now().toString(),auth.currentUser!!.email.toString(),pasto)
        }
    }

    private fun calculateFabbisogno(utente: Utente) : Double{
        val today = LocalDate.now()
        val birthday: LocalDate = LocalDate.parse(utente.data_nascita)
        val period: Period = Period.between(birthday, today)
        if(utente.sesso == "uomo")
            return (66 + (13.7 * utente.peso_attuale) + (5 * utente.altezza) - (6.8 * period.years)) * utente.LAF
        else
            return (65 + (9.6 * utente.peso_attuale) + (1.8 * utente.altezza) - (4.7 * period.years)) * utente.LAF


    }

}
