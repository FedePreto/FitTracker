package com.example.fittracker.prodotto

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.DiarioDB
import com.example.fittracker.databaseFB.ProdottoDB
import com.example.fittracker.databaseFB.UtenteDB
import com.example.fittracker.model.Diario
import com.example.fittracker.model.Pasto
import com.example.fittracker.model.Utente
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProdottoViewModel : ViewModel() {

    private val prodottoDB = ProdottoDB()
    private val utenteDB = UtenteDB()
    private val diarioDB = DiarioDB()
    private val auth = FirebaseAuth.getInstance()
    private var hashMapCalorie = HashMap<String, Int>()


    fun setPastoOnDB(
        tipologiaPasto: String, foodId: String, image: String, nome: String/*label*/, calorie: Double, proteine: Double,
        carboidrati: Double, grassi: Double, quantita: Int, context: Context
    ) {
        viewModelScope.launch {
            if (prodottoDB.setPasto(
                    auth.currentUser?.email!!,
                    LocalDate.now().toString(), tipologiaPasto, foodId, image, nome/*label*/, calorie, proteine,
                    carboidrati, grassi, quantita
                )
            ) {
                Toast.makeText(context, "$quantita $nome aggiunto/i al tuo Diario", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Aggiunta pasto al Diario fallita", Toast.LENGTH_LONG).show()
        }
    }

    private fun setChiloCalorie() {
        viewModelScope.launch {
            val diario = diarioDB.getUserDiario(auth.currentUser!!.email!!)
            Log.d("Pasto", diario!!.utente.toString())
            val tipologiaPasto = arrayListOf<String>("COLAZIONE", "PRANZO", "CENA", "SPUNTINO")
            for (pasto in tipologiaPasto)
                hashMapCalorie.put(pasto, 0)
            Log.d("Pasto", hashMapCalorie.toString())
            for (pasto in tipologiaPasto) {
                val arrayProdotti = prodottoDB.getProdotti(LocalDate.now().toString(), auth.currentUser?.email!!, pasto)
                setMacroDiario(arrayProdotti, pasto)
            }
            Log.d("Pasto", hashMapCalorie.toString())
            diarioDB.setDiario(
                auth.currentUser?.email!!, LocalDate.now().toString(), diario!!.fabbisogno,
                diario!!.grassiTot, diario!!.proteineTot, diario!!.carboidratiTot,
                diario!!.chiloCalorieEsercizio, hashMapCalorie.get("COLAZIONE")!!,
                hashMapCalorie.get("PRANZO")!!, hashMapCalorie.get("CENA")!!,
                hashMapCalorie.get("SPUNTINO")!!, diario!!.acqua
            )
        }
    }


    private fun setMacroDiario(arrayProdotti: List<Pasto>?, pasto: String) {
        if (arrayProdotti != null)
            if (arrayProdotti.isNotEmpty()) {
                var calorie = 0.0
                var proteine = 0.0
                var carboidrati = 0.0
                var grassi = 0.0
                for (prodotto in arrayProdotti) {
                    calorie += prodotto.calorie * prodotto.quantita
                    proteine += prodotto.proteine * prodotto.quantita
                    carboidrati += prodotto.carboidrati * prodotto.quantita
                    grassi += prodotto.grassi * prodotto.quantita

                }
                hashMapCalorie.set(pasto, calorie.toInt())

            }
    }
}
