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
     //order enter que indica quin apat del dia es
     fun setFood(date:String,order:Int,name: String) {
          //TODO test this funtionality
          if(existsThisDay(date)) {
               user.setFood(date,order,name)
          }
     }
     fun existsThisDay(date:String):Boolean{
          return user.existsThisDay(date)
     }
}