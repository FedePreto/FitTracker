package com.example.fittracker.model.Json_Parsing

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Esercizio(
    @field:Json(name = "calories_per_hour")val calorie_ora: Int,
    @field:Json(name = "duration_minutes")val durata_minuti: Int,
    @field:Json(name = "name")val nome: String,
    @field:Json(name = "total_calories")val calorie_totali: Int
)