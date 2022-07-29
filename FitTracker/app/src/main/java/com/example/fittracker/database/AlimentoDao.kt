package com.example.fittracker.database

import androidx.room.*


@Dao
interface AlimentoDao {
    @Query("select * from alimento")
    fun getAllAlimenti(): List<Alimento>


}