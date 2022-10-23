package com.example.fittracker.diario

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.DiarioDB
import com.example.fittracker.databaseFB.FirebaseDB
import com.example.fittracker.model.Diario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.launch

class DiarioViewModel : ViewModel() {

    private val diarioDB = DiarioDB()
    private val auth = FirebaseAuth.getInstance()

    private var _diario = MutableLiveData(Diario())
    val diario: LiveData<Diario>
        get() = _diario

    var isFull = arrayOf(false,false,false,false,false,false,false,false)


    fun setDiarioOnDB(data:String, grassiTot:Int, proteineTot:Int, carboidratiTot:Int,
                              chiloCalorieEsercizio:Int, chiloCalorieColazione:Int, chiloCaloriePranzo:Int,
                              chiloCalorieCena:Int, chiloCalorieSpuntino:Int,acqua:ArrayList<Boolean>){
        viewModelScope.launch {
            diarioDB.setDiario(auth.currentUser?.email!!,data, grassiTot, proteineTot, carboidratiTot, chiloCalorieEsercizio,
                                chiloCalorieColazione, chiloCaloriePranzo, chiloCalorieCena, chiloCalorieSpuntino,acqua)
        }
    }

   fun getUserDiarioDB(){
       viewModelScope.launch {
           _diario.value = diarioDB.getUserDiario(auth.currentUser?.email!!)
       }
    }






}