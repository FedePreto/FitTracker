package com.example.fittracker.model

<<<<<<< Updated upstream
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
=======
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
=======
) : Parcelable {
>>>>>>> Stashed changes
    constructor(): this("","","",-1,"","",0,0.0,0.0,0.0,"")

}