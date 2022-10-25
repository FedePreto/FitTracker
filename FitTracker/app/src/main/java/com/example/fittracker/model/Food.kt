/*package com.example.fittracker.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Food (
    val foodId : String,
    val label : String,
    val knownAs : String,
    val nutrients : Nutrients,
    val category : String,
    val categoryLabel : String,
    val foodContentsLabel : String?){


}

@JsonClass(generateAdapter = true)
data class Nutrients(
    @field:Json(name = "ENERC_KCAL") val chiloCalorie : Double, //Kcal
    @field:Json(name = "PROCNT") val proteine : Double, //g
    @field:Json(name = "FAT") val grassi : Double, // g
    @field:Json(name = "CHOCDF") val carboidrati : Double, //g
    @field:Json(name = "FIBTG") val fibre : Double) //g
{
}

 */