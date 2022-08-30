package com.example.fittracker.databaseRoom

import androidx.room.*


@Dao
interface AlimentoDao {
    @Query("select * from alimento")
    fun getAllAlimenti(): List<Alimento>


}