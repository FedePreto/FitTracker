package com.example.fittracker.retrofit

import com.example.fittracker.model.Json_Parsing.Esercizio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEserciziInterface {
    @GET("/v1/caloriesburned")
    fun getEsercizi(@Query("X-Api-Key") api_key : String,
                    @Query("activity") activity : String): Call<ArrayList<Esercizio>>
}