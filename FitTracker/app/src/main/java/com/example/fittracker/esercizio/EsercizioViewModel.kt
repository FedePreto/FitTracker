package com.example.fittracker.esercizio

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.DiarioDB
import com.example.fittracker.databaseFB.EsercizioDB
import com.example.fittracker.databaseFB.PreferitiDB
import com.example.fittracker.databaseFB.ProdottoDB
import com.example.fittracker.model.Pasto
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate

class EsercizioViewModel : ViewModel(){

    private val esercizioDB = EsercizioDB()
    private val preferitiDB = PreferitiDB()
    private val diarioDB = DiarioDB()
    private val auth = FirebaseAuth.getInstance()
    private var hashMapCalorie = HashMap<String, Int>()
    private var hashMapMacro = HashMap<String, Int>()

    fun setEsercizioOnDB(nome: String/*label*/, calorieOra: Int, durata: Int, context: Context) {
        viewModelScope.launch {
            if (esercizioDB.setEsercizio(auth.currentUser?.email!!, LocalDate.now().toString(), nome/*label*/,calorieOra, durata)) {
                Toast.makeText(context, "$nome svolto per $durata minuti aggiunto al tuo Diario", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Aggiunta esercizio al Diario fallita", Toast.LENGTH_LONG).show()
        }
    }



    fun setEsercizioPreferitiOnDB(nome: String/*label*/, calorieOra: Int, context: Context)
    {
        viewModelScope.launch {
            if (preferitiDB.setEsercizioPreferiti(auth.currentUser?.email!!, nome/*label*/, calorieOra)) {
                Toast.makeText(context, "$nome aggiunto/i ai Preferiti", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Aggiunta esercizip ai Preferiti fallita", Toast.LENGTH_LONG).show()
        }
    }

    private fun setChiloCalorie() {
        viewModelScope.launch {
            val diario = diarioDB.getUserDiario(auth.currentUser!!.email!!)
            val arrayEsercizi = esercizioDB.getEsercizi(LocalDate.now().toString(), auth.currentUser?.email!!)
            var calorie_esercizio = 0.0
            if (arrayEsercizi != null) {
                if (arrayEsercizi.isNotEmpty()) {
                    for (esercizio in arrayEsercizi){
                        Log.d("esercizio",calorie_esercizio.toString())
                        calorie_esercizio += esercizio.calorieOra * (esercizio.durata/60.0)
                    }
                }
            }
            diarioDB.setDiario( auth.currentUser?.email!!, LocalDate.now().toString(), diario!!.fabbisogno,
                diario.grassiTot,  diario.proteineTot,  diario.carboidratiTot, calorie_esercizio.toInt(),
                diario.chiloCalorieColazione, diario.chiloCaloriePranzo, diario.chiloCalorieCena,diario.chiloCalorieSpuntino
                , diario.acqua)
        }
    }
}