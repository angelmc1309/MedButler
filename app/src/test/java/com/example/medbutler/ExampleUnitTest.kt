package com.example.medbutler

import android.R
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.dataBase.DAOUser
import com.example.medbutler.classes.model.Usuari
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val dao = DAOUser()
        val user = Usuari("test","test","13","potato")
        dao.save(user)
    }
}
