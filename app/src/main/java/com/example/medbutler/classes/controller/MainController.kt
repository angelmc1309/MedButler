package com.example.medbutler.classes.controller

import com.example.medbutler.classes.dataBase.DAOUser
import com.example.medbutler.classes.model.Usuari
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MainController {
     /*Unique instance of the controller*/
     object Singleton
     val user = null
     val daoUser = DAOUser()
     var firebase_auth: FirebaseAuth=FirebaseAuth.getInstance()
     var db= FirebaseFirestore.getInstance()

     fun getCurrentUser(): FirebaseUser? {
          val user = FirebaseAuth.getInstance().currentUser
          //val usermail= user?.email
         // val x=db.collection("users").document(usermail)
          if (user != null) {
               return user
          }else{
               return null
          }

     }
     fun getCurrentUserInfo(): String {

         /* var s= getCurrentUser()?.email
          lateinit var name:String
          //val doc: DocumentReference? = s?.let { db.collection("users").document(it) }
          val doc:DocumentReference=FirebaseFirestore.getInstance().document("users/admin@gmail.com")
          doc.get().addOnSuccessListener(OnSuccessListener<DocumentSnapshot> {documentSnapshot ->
               val note=documentSnapshot.toObject(ContactsContract.CommonDataKinds.Note::class.java)

          })
          return name*/
          lateinit var name:String
          val docRef = db.collection("users").document("q@gmail.com")
          docRef.get().addOnSuccessListener { documentSnapshot ->
               val uuser= documentSnapshot.toObject(Usuari::class.java)
               if (uuser != null) {
                    name=uuser.getFullname()
               }
          }
          return name
     }

fun Enregistrar(
     email: String,
     fullname: String,
     password: String,
     birthday: String,
     height:String,
     weight:String, genderr:String
){


     firebase_auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{  task: Task<AuthResult> ->
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

          }
}
}









}
