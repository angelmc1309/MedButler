package com.example.medbutler





import com.example.medbutler.classes.controller.MainController
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MedAddTest {
    @Test
    fun addMedTest() {

        MainController.addMed("ib600","IBUPROFE 600mg",24,60,0,
            12,true)
        Assert.assertEquals(MainController.getMedList(),"[IBUPROFE 600mg]")
        MainController.addMed("ib500","IBUPROFE 500mg",8,60,0,
            12,true)
        Assert.assertEquals(MainController.getMedList(),"[IBUPROFE 600mg, IBUPROFE 500mg]")
    }
    @Test
    fun removeMed(){
        MainController.removeMed("ib500")
        Assert.assertEquals(MainController.getMedList(),"[IBUPROFE 600mg]")
    }
}
