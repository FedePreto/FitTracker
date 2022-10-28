package com.example.fittracker.diete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.DietaDB
import com.example.fittracker.model.Dieta
import kotlinx.coroutines.launch

class DieteViewModel : ViewModel() {

    private var _dieteLiveData = MutableLiveData<List<Dieta>>()
    val dieteLiveData : LiveData<List<Dieta>>
        get() = _dieteLiveData
    private val dieteDB = DietaDB()


    fun getDiete(){
        viewModelScope.launch {
            _dieteLiveData.value = dieteDB.getDiete()
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