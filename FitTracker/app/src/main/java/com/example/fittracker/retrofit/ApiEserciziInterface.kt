package com.example.fittracker.retrofit

import com.example.fittracker.model.Json_Parsing.EserciziList
import com.example.fittracker.model.Json_Parsing.Esercizio
import org.checkerframework.checker.units.qual.A
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import retrofit2.Call
import retrofit2.http.*

interface ApiEserciziInterface {


    @GET("/v1/caloriesburned")
    fun getEsercizi(@Header("X-Api-Key") key : String,
                    @Query("activity") activity : String): Call<List<Esercizio>>
}