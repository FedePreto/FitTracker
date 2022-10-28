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


    fun calcoloMacro(kcal: Int, dieta:String, perc_carboidrati: Int, perc_proteine: Int, perc_grassi:Int){
        val kcal_giornaliere = kcal
        val perc_carb = perc_carboidrati
        val perc_prot = perc_proteine
        val perc_gras = perc_grassi

        val grammi_carboidrati = ((kcal_giornaliere*perc_carb)/100)/4
        val grammi_proteine = ((kcal_giornaliere*perc_prot)/100)/4
        val grammi_grassi = ((kcal_giornaliere*perc_gras)/100)/9

    }




}