package com.example.fittracker.retrofit

import com.example.fittracker.utils.APICredentials
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitEserciziInstance {
    val api_esercizi : ApiEserciziInterface by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory
                                .create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
                                )
            .baseUrl(APICredentials.BASE_URL_ESERCIZI)
            .build().create(ApiEserciziInterface::class.java)
        }
    }