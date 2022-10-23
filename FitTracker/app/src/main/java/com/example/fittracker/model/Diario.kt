package com.example.fittracker.model

data class Diario(
    val utente : String,
    val data : String,
    val grassiTot : Int,
    val proteineTot : Int,
    val carboidratiTot : Int,
    val chiloCalorieEsercizio : Int,
    val chiloCalorieColazione : Int,
    val chiloCaloriePranzo : Int,
    val chiloCalorieCena : Int,
    val chiloCalorieSpuntino : Int,
    val acqua : ArrayList<Boolean>

) { constructor(): this("","",0,0,0,0,0,0,0,0, ArrayList<Boolean>())
}