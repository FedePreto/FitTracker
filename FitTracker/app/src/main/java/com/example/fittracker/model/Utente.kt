package com.example.fittracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Utente(
        var nome: String,
        var cognome: String,
        var email: String,
        var LAF: Double,
        var agonista: Boolean,
        var sesso: String,
        var data_nascita: String,
        var altezza: Int,
        var peso_attuale: Double,
        var sport: String?,
        var dieta: String

) : Parcelable {    constructor(): this("","","",0.0,false,"","",0,0.0,"","Mediterranea")

}