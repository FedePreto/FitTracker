package com.example.fittracker.diario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fittracker.databaseFB.DiarioDB
import com.example.fittracker.model.Diario

class DiarioViewModel : ViewModel() {

    private val diarioDB = DiarioDB()

    private var _diario = MutableLiveData(Diario())
    val diario: LiveData<Diario>
        get() = _diario

    var isFull = arrayOf(false,false,false,false,false,false,false,false)



}