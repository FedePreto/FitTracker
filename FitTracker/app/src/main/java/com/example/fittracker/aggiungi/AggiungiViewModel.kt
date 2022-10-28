package com.example.fittracker.aggiungi

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fittracker.databaseFB.PreferitiDB
import com.example.fittracker.model.Json_Parsing.Json_FoodList
import com.example.fittracker.model.Json_Parsing.Prodotto
import com.example.fittracker.model.Pasto
import com.example.fittracker.retrofit.RetrofitInstance
import com.example.fittracker.utils.APICredentials
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate


class AggiungiViewModel : ViewModel() {

    private val preferitiDB = PreferitiDB()
    private val auth = FirebaseAuth.getInstance()

    private var _foodLiveData = MutableLiveData<ArrayList<Prodotto>>()

    val foodLiveData : LiveData<ArrayList<Prodotto>>
        get() = _foodLiveData

    private var _preferitiLiveData = MutableLiveData<List<Pasto>>()
    val preferitiLiveData: LiveData<List<Pasto>>
        get() = _preferitiLiveData

    fun getFoodFromNameorUPC(ingr : String, upc: String) {

        RetrofitInstance.api.getFoodFromNameOrUPC(APICredentials.API_ID,APICredentials.API_KEY,ingr,upc)
            .enqueue(object : Callback<Json_FoodList> {
                override fun onResponse(call: Call<Json_FoodList>, response: Response<Json_FoodList>) {
                    if (response.body() != null) {
                        var hints = response.body()!!.hints!!
                        var foods : ArrayList<Prodotto> = arrayListOf()
                        for (i in 0..hints.size - 1)
                            foods.add(hints[i].food!!)
                        _foodLiveData.value = foods
                    } else {
                        return
                    }
                }

                override fun onFailure(call: Call<Json_FoodList>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })

    }

    fun getPreferiti(tipologiaPasto : String){
        viewModelScope.launch {
            _preferitiLiveData.value =
                preferitiDB.getPastiPreferiti(LocalDate.now().toString(),auth.currentUser!!.email!!, tipologiaPasto)
        }
    }

}