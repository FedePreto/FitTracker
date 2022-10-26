package com.example.fittracker.aggiungi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fittracker.model.Json_Parsing.Json_FoodList
import com.example.fittracker.model.Json_Parsing.Json_Hint
import com.example.fittracker.model.Prodotto
import com.example.fittracker.retrofit.ApiInterface
import com.example.fittracker.retrofit.RetrofitInstance
import com.example.fittracker.utils.APICredentials
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class AggiungiViewModel : ViewModel() {


    private var _foodLiveData = MutableLiveData<ArrayList<Prodotto>>()

    val foodLiveData : LiveData<ArrayList<Prodotto>>
        get() = _foodLiveData

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

}