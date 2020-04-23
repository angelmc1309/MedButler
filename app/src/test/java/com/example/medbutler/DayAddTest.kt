package com.example.medbutler

import com.example.medbutler.classes.controller.MainController
import org.junit.Assert
import org.junit.Test

class DayAddTest {
    @Test
    fun addDayTest(){
        MainController.createDay("17/04/2020")
        Assert.assertEquals(MainController.getDayList(),"[17/04/2020]")
        MainController.createDay("18/04/2020")
        Assert.assertEquals(MainController.getDayList(),"[17/04/2020, 18/04/2020]")
        MainController.removeDay("18/04/2020") //Tambe provem el remove, ja que en una funcio apart em dona problemes
        Assert.assertEquals(MainController.getDayList(),"[17/04/2020]")
    }
    /*@Test
    fun removeDayTest(){
        MainController.removeDay("18/04/2020")
        Assert.assertEquals(MainController.getDayList(),"[17/04/2020]")
        //Remove funciona, exemple a dalt, el que no funciona és aquest test
        //No agafa el calendari que es crea anteriorment
    }*/
}