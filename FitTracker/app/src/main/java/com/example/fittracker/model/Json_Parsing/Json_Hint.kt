package com.example.fittracker.model.Json_Parsing

import com.example.fittracker.model.Prodotto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Json_Hint(
    val food: Prodotto?
)