package com.example.fittracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Utente(
        var nome: String,
        var cognome: String,
        var email: String,
        var stile_di_vita: Int,
        var agonista: Boolean,
        var sesso: String,
        var data_nascita: String,
        var altezza: Int,
        var peso_attuale: Double,
        var sport: String?

) : Parcelable {    constructor(): this("","","",-1,false,"","",0,0.0,"")

}