package com.example.medbutler.classes.controller

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.medbutler.classes.dataBase.DAOUser
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

class MainController {
    /*Unique instance of the controller*/
    object Singleton

    var user:Usuari = Usuari()
    val daoUser = DAOUser()
    var firebase_auth: FirebaseAuth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()
    var userdb = db.collection("users")
    fun getCurrentUser(): FirebaseUser? {
        val user = FirebaseAuth.getInstance().currentUser
        //val usermail= user?.email
        // val x=db.collection("users").document(usermail)
        if (user != null) {
            return user
        } else {
            return null
        }

    }
  suspend fun getCurrentUserInfo():Usuari?{


        //var docRef = db.collection("users").document("m@gmail.com")
        var u:Usuari?=null
        /*try {
            val snaps=docRef.get().await()
            val u=snaps.toObject(Usuari::class.java)
            if (u != null) {
                getu(u)
            }
        }catch (e: FirebaseFirestoreException){

        }*/
        return try {
            val dara=db.collection("users").document("m@gmail.com").get().addOnSuccessListener {
                    /*documentSnapshot ->
                val uuser = documentSnapshot.toObject(Usuari::class.java)*/
                result->

                Log.d(TAG, result.getString("username"))

                /*val uuser=result.toObject(Usuari::class.java)
                if (uuser != null) {
                    u=uuser
                }*/
            }.await()
            return u
        }catch (e: FirebaseFirestoreException){
            return null
        }
     //   return name
    }
    fun getu(u:Usuari){
        this.user=u
    }

    fun getuu():Usuari{
        runBlocking{
            getCurrentUserInfo()?.let { getu(it) }
        }
        return user
    }

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

/*fun Enregistrar(
     email: String,
     fullname: String,
     password: String,
     birthday: String,
     height:String,
     weight:String, genderr:String
){


     firebase_auth.createUserWithEmailAndPassword(email,password)
    val user=Usuari(email,fullname,birthday,height,weight,genderr)
    print(user.toStringg())
    userdb.document(email).set(user)
         /*.addOnCompleteListener{  task: Task<AuthResult> ->
          if(task.isSuccessful){
               /*val user= hashMapOf(
                    "Fullname" to fullname,
                    "Birthday" to birthday,
                    "Email" to email,
                    "Gender" to genderr,
                    "Height" to height,
                    "Weight" to weight

               )*/
               val user=Usuari(email,fullname,birthday,height,weight,genderr)
               db.collection("users").document(email).set(user)

          }*/
}*/
    }











