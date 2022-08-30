package com.example.fittracker.databaseRoom

import android.content.Context
import androidx.room.*


@Database(entities = [Alimento::class], version=1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun alimentoDao(): AlimentoDao



    companion object{
        private var INSTANCE: AppDatabase? =  null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "FitTRacker_DB",
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                //return instance
                instance
            }

        }
    }
}