package com.example.fittracker.model

data class Utente(
    val name: String,
    val lastname: String,
    val email: String,
    val obbiettivo: Int,
    val sesso: String,
    val data_nascita: String,
    val altezza: Int,
    val peso_attuale: Double,
    val peso_obbiettivo: Double?,
    val kg_settimanali: Double?,
    val data_raggiungimento: String?

    ){

    constructor(): this("","","",-1,"","",0,0.0,0.0,0.0,"")

}