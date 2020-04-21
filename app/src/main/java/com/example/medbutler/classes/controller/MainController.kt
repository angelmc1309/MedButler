package com.example.medbutler.classes.controller

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.medbutler.classes.dataBase.DAOUser
import com.example.medbutler.classes.model.Calendar
import com.example.medbutler.classes.model.Day
import com.example.medbutler.classes.model.Usuari
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlinx.coroutines.*

object MainController {
    /*Unique instance of the controller*/
    //object Singleton

    var user: Usuari = Usuari()
    val daoUser = DAOUser()
    var firebase_auth: FirebaseAuth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()
    var userdb = db.collection("users")

    val usuariProvaEdu:Usuari=
        Usuari("oscar","Oscar Coronavirus",
            "22/12/2008","180 cm","60 kg")



    /* fun test()= runBlocking<Unit> {

        getCurrentUserInfo()?.let { getu(it) }

    }*/

    fun Enregistrar(user: Usuari, passwor: String) {
        user.getusername()?.let { firebase_auth.createUserWithEmailAndPassword(it, passwor) }
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    user.getusername()?.let { userdb.document(it).set(user) }
                }

            }
    }

     fun addMed(
          id: String, name: String, period: Int, duration: Int, startTimeMinute: Int,
          startTimeHour: Int, allowNotification: Boolean
     ) {
          user.addMed(id, name, period, duration, startTimeMinute, startTimeHour,
               allowNotification)

    }

    fun getMedList(): String {
        return user.getMedList()
    }

    fun removeMed(id: String) {
        user.removeMed(id)
    }
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

    fun getUsuariPerProvarEdu(): Usuari {
        return usuariProvaEdu
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