package com.example.fittracker.databaseRB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProdottoDao {
    @Query("SELECT * FROM prodottiPers")
    fun getAll(): ArrayList<Prodotto>

    @Insert

}