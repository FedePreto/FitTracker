package com.example.fittracker.profilo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.DiarioDB
import com.example.fittracker.databaseFB.UtenteDB
import com.example.fittracker.model.Utente
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

class ProfiloViewModel : ViewModel() {

    private val utenteDB = UtenteDB()
    private val diarioDB = DiarioDB()

    private var _profilo = MutableLiveData<Utente>()

    val profilo : LiveData<Utente>
        get() = _profilo

    val _diarioUpdated = MutableLiveData<Boolean>()
    val diarioUpdated : LiveData<Boolean>
        get() = _diarioUpdated

    private val auth = FirebaseAuth.getInstance()

    fun changePassword(email : String) : Task<Void> {
        return auth.sendPasswordResetEmail(email)
    }

    fun getDataProfilo(){
        viewModelScope.launch {
           _profilo.value =  utenteDB.getUtente(auth.currentUser!!.email!!)
        }
    }
    fun updateAuthUtenteOnDB(
        nome:String, cognome:String, email:String, LAF:Double, agonistico:Boolean,
        sesso:String, data_nascita: String, altezza:Int, peso_attuale:Double,
        sport:String?, contesto: Context
    ){
        try {
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = "$nome $cognome"
            }
            user!!.updateProfile(profileUpdates)
            viewModelScope.launch { utenteDB.updateUtente(nome, cognome, email,LAF, agonistico ,sesso, data_nascita, altezza,
                peso_attuale, sport, contesto)
                val fabbisogno = calculateFabbisogno(data_nascita,sesso,peso_attuale,altezza,LAF)
                val diario = diarioDB.getUserDiario(auth.currentUser!!.email!!)!!
            diarioDB.setDiario(auth.currentUser!!.email!!, LocalDate.now().toString(),fabbisogno,diario.grassiTot,
                diario.proteineTot,diario.carboidratiTot,diario.chiloCalorieEsercizio,diario.chiloCalorieColazione,
                diario.chiloCaloriePranzo,diario.chiloCalorieCena,diario.chiloCalorieSpuntino,diario.acqua)
                _diarioUpdated.value = true
            }
        } catch (e: Exception) {
        }
    }

    private fun calculateFabbisogno(data_nascita :String, sesso: String,peso_attuale: Double,altezza:Int,LAF:Double) : Int{
        val today = LocalDate.now()
        val birthday: LocalDate = LocalDate.parse(data_nascita)
        val period: Period = Period.between(birthday, today)
        if(sesso == "uomo")
            return ((66 + (13.7 * peso_attuale) + (5 * altezza) - (6.8 * period.years)) * LAF).toInt()
        else
            return ((65 + (9.6 * peso_attuale) + (1.8 * altezza) - (4.7 * period.years)) * LAF).toInt()


    }



}