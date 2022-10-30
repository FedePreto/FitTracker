package com.example.fittracker.retrofit

import com.example.fittracker.model.Json_Parsing.EserciziList
import com.example.fittracker.model.Json_Parsing.Json_FoodList
import com.example.fittracker.model.Json_Parsing.Json_Hint
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    //Call è un metodo di Retrofit che consente di mandare una richiesta ad un webserver ed ottenere una risposta.
    @GET("/api/food-database/v2/parser")
    fun getFoodFromNameOrUPC(@Query("app_id") idApp : String,
                             @Query("app_key") keyApp : String,
                             @Query("ingr") ingredient : String,
                             @Query("upc") upc : String): Call<Json_FoodList>

    @GET("/v1/caloriesburned")
    fun getEsercizi( @Query("X-Api-Key") api_key : String,
                    @Query("activity") activity : String): Call<EserciziList>


}