package com.example.medbutler

import com.example.medbutler.classes.controller.MainController
import org.junit.Assert
import org.junit.Test

class SetFoodTest {
    @Test
    fun setFoodTest(){
        MainController.createDay("18/04/2020")
        MainController.setFood("18/04/2020",0,"Poma")
        Assert.assertEquals(MainController.getFood("18/04/2020"),"[Poma, , , , ]")
    }
}