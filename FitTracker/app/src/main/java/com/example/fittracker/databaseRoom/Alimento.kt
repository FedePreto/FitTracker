package com.example.fittracker.databaseRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "alimento")
data class Alimento(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val name:String,
    val marca:String,
    val calorie:Int,
    val quantita:Int,
    val categoria:String

)