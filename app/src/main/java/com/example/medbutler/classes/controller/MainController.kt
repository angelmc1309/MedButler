package com.example.medbutler.classes.controller

import com.example.medbutler.classes.dataBase.DAOUser
import com.example.medbutler.classes.model.Calendar
import com.example.medbutler.classes.model.Day
import com.example.medbutler.classes.model.Usuari

object MainController {
     /*Unique instance of the controller*/

     private var user: Usuari = Usuari("", "", "", "")

     private val daoUser = DAOUser()

     private var calendar: Calendar = Calendar()


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
          }else{
               user.addDay(date)
               user.setFood(date, order, name)
          }
     }
     fun existsThisDay(date:String):Boolean{
          return user.existsThisDay(date)
     }

     fun createDay(date: String){
          if(!existsThisDay(date)){
              user.addDay(date)
          }
     }

     fun getDayList():String{
          return user.getDayList()
     }

     fun removeDay(date: String){
          user.removeDay(date)
     }

     fun getFood(date:String):String?{
          if(existsThisDay(date)){
               return user.getFood(date)
          }else{
               return null
          }
     } //Preguntar com solucionar lo del interrogant
}