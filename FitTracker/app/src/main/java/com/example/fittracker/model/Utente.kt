package com.example.fittracker.model

data class Utente(
    val name: String,
    val lastname: String,
    val email: String,
    val obbiettivo: Int,
    val sesso: String,
    val data_nascita: String,
    val altezza: Int,
    val peso_attuale: Int,
    val peso_obbiettivo: Int?,
    val kg_settimanali: Int?,
    val data_raggiungimento: String?

    ){

    constructor(): this("","","",-1,"","",0,0,0,0,"")

}