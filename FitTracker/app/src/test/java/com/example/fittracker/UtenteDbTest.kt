package com.example.fittracker

import com.example.fittracker.model.Utente
import com.example.fittracker.profilo.ProfiloViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UtenteDbTest {

    @Before
    fun setUp(){

    }

    // Test per vedere se il metodo calculateFabbisogno calcola correttamente le calorie giornaliere

    //SUCCESSO
    @Test
    fun check_CorrectFabbisogno(){
        val fabbisogno = Utente()
        val utente = Utente("Alessandro","Rongoni","alerong@gmail.com",1.725,true,"Uomo","2000-05-18",183,76.0,"Calcio","Climatica")
        Assert.assertEquals(3230, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }

    //FALLIRE
    @Test
    fun check_FakeFabbisogno(){
        val fabbisogno = Utente()
        val utente = Utente("Alessandro","Rongoni","alerong@gmail.com",1.725,true,"Uomo","2000-05-18",183,76.0,"Calcio","Climatica")
        Assert.assertEquals(2650, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }

    //SUCCESSO
    @Test
    fun check_LAFSedentarioFabbisogno(){
        val fabbisogno = Utente()
        val utente = Utente("Alessandro","Rongoni","alerong@gmail.com",1.2,true,"Uomo","2000-05-18",183,76.0,"Calcio","Climatica")
        Assert.assertEquals(2247, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }

    //SUCCESSO
    @Test
    fun check_DonnaFabbisogno(){
        val fabbisogno = Utente()
        val utente = Utente("Martina","Rossi","martina.rossiOF.com",1.55,false,"Donna","2002-06-23",167,55.0,"","Climatica")
        Assert.assertEquals(1239, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }

    //FALLIRE
    @Test
    fun check_DonnaCambioEtaFabbisogno(){
        val fabbisogno = Utente()
        val utente = Utente("Martina","Rossi","martina.rossiOF.com",1.55,false,"Donna","1999-06-23",167,55.0,"","Climatica")
        Assert.assertEquals(1239, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }


}
