package com.example.fittracker.utils

import java.util.Objects


//Utilizzo questa classe per cambiare facilmente ID , CHIAVE e URL base nel csao ci fosse bisogno
//Uso il companion object per poter accedere in maniera statica alle variabili
class APICredentials {
    companion object {
        @JvmStatic
        val BASE_URL: String = "https://api.edamam.com"
        @JvmStatic
        val API_ID: String = "a4e62224"
        @JvmStatic
        val API_KEY: String = "2b0adbda47d1293d35d373d9f2ffcee1"
    }
}
