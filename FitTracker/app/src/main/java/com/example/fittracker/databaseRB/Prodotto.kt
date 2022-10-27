package com.example.fittracker.databaseRB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prodottiPers")
data class Prodotto (
    @PrimaryKey(autoGenerate = true) val _id: Int,
    @ColumnInfo(name = "titolo_pasto")val titolo: String,
    @ColumnInfo(name = "calorie_pasto ") val kcal: Int,
    @ColumnInfo(name = "carboidrati_pasto") val carb: Double?,
    @ColumnInfo(name = "grassi_pasto") val grassi: Double?,
    @ColumnInfo(name = "proteine_pasto") val prot: Double?

    )