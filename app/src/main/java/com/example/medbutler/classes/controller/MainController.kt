package com.example.medbutler.classes.controller

import com.example.medbutler.classes.dataBase.DAOUser
import com.example.medbutler.classes.model.Usuari

object MainController {
     /*Unique instance of the controller*/

     private var user: Usuari = Usuari("", "", "", "")

     private val daoUser = DAOUser()


     fun addMed(
          id: String, name: String, period: Int, duration: Int, startTimeMinute: Int,
          startTimeHour: Int, allowNotification: Boolean
     ) {
          user.addMed(id, name, period, duration, startTimeMinute, startTimeHour,
               allowNotification)

     }
     fun getMedList():String{
         return  user.getMedList()
     }
     fun removeMed(id: String){
          user.removeMed(id)
     }

}