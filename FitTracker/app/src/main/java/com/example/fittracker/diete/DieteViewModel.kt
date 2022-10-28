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




}