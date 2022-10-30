package com.example.fittracker

import com.example.fittracker.diario.DiarioViewModel
import com.example.fittracker.model.Utente
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class UtenteDbTEst {

    @Before
    fun setUp(){

    }

    // Test per vedere se il metodo calculateFabbisogno calcola correttamente le calorie giornaliere
    @Test
    fun check_Fabbisogno1(){
        val fabbisogno = DiarioViewModel()
        val utente = Utente("Alessandro","Rongoni","alerong@gmail.com",1.725,true,"Uomo","2000-05-18",183,76.0,"Calcio","Climatica")
        Assert.assertEquals(1760.0, fabbisogno.calculateFabbisogno(utente))
    }


    @Test
    fun check_Fabbisogno2(){
        val fabbisogno = DiarioViewModel()
        val utente = Utente("Alessandro","Rongoni","alerong@gmail.com",1.725,true,"Uomo","2000-05-18",183,76.0,"Calcio","Climatica")
        Assert.assertEquals(3230, fabbisogno.calculateFabbisognoTest(utente))
    }
}