package com.example.fittracker.prodotto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.ProdottoDB
import com.example.fittracker.databaseFB.UtenteDB
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProdottoViewModel : ViewModel() {

    private val prodottoDB = ProdottoDB()
    private val utenteDB = UtenteDB()
    private val auth = FirebaseAuth.getInstance()


    fun setPastoOnDB(tipologiaPasto: String, foodId:String, image:String, nome:String/*label*/, calorie:Double, proteine:Double,
                      carboidrati:Double, grassi:Double, quantita : Int){
        viewModelScope.launch {
            prodottoDB.setPasto(auth.currentUser?.email!!,
                LocalDate.now().toString(),tipologiaPasto, foodId, image, nome/*label*/, calorie, proteine,
                carboidrati, grassi, quantita)
        }
    }
}