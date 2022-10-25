package com.example.fittracker.model.Json_Parsing

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Json_Next(
    val href: String?,
    val title: String?
)