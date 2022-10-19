package com.example.fittracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Utente(
        var nome: String,
        var cognome: String,
        var email: String,
        var obbiettivo: Int,
        var sesso: String,
        var data_nascita: String,
        var altezza: Int,
        var peso_attuale: Double,
        var peso_obbiettivo: Double?,
        var kg_settimanali: Double?,
        var data_raggiungimento: String?


) : Parcelable {    constructor(): this("","","",-1,"","",0,0.0,0.0,0.0,"")

}