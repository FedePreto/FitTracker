package com.example.fittracker.model.Json_Parsing

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EserciziList : ArrayList<Esercizio>()