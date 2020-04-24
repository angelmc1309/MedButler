package com.example.medbutler.classes.dataBase

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.medbutler.Login
import com.example.medbutler.MainActivity
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Usuari
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.system.exitProcess

class DAOUser : DAO<Usuari> {
    var firebase_auth: FirebaseAuth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()
    var userdb = db.collection("users")
    val user = FirebaseAuth.getInstance().currentUser


    override fun get(id: String) {
    }
    fun get(){
        if (user != null) {
            user.email?.let {
                db.collection("users").document(it).get().addOnSuccessListener {

                    val uuser=it.toObject(Usuari::class.java)
                    if (uuser != null) {
                        MainController.setCurrentUser(uuser)
                    }
                }
            }
        }
    }

    override fun delete(obj: Usuari) {

    }
    fun delete(email:String){
        if (user != null) {
            user.delete().addOnCompleteListener{
                if(it.isSuccessful){
                    userdb.document(email).delete()
                }
            }
        }
    }

    override fun getAll() {

    }

    override fun save(obj: Usuari) {

    }
    fun save(obj: Usuari, password:String) {
        obj.getusername()?.let {
            firebase_auth.createUserWithEmailAndPassword(it, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        obj.getusername()?.let { userdb.document(it).set(obj) }
                    }

                }
        }
    }

    fun saveListMed(){
        userdb.document(MainController.getcurrent().getusername()).set(MainController.getcurrent())
    }

    override fun update(obj: Usuari, params: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun login(context:Context,username:String, password: String) {
        this.firebase_auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent= Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    MainController.initFirestore()

                } else {
                    var e: FirebaseAuthException = task.exception as FirebaseAuthException
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
    }
    fun signOut(){
        firebase_auth.signOut()
    }
    fun changePassword(newPassword:String){

            user!!.updatePassword(newPassword)

    }
    fun changeEmail(newEmail:String){
        if (user != null) {
            user.email?.let {
                userdb.document(it).update(mapOf(
                    "username" to newEmail
                ))
            }
        }
        user!!.updateEmail(newEmail)

    }
    fun updatefullname(newname:String){
        if (user != null) {
            user.email?.let {
                userdb.document(it).update(mapOf(
                    "fullname" to newname
                ))
            }
        }
    }
    fun updateheight(newheight:String){
        if (user != null) {
            user.email?.let {
                userdb.document(it).update(mapOf(
                    "height" to newheight
                ))
            }
        }
    }
    fun updateweight(newweight:String){
        if (user != null) {
            user.email?.let {
                userdb.document(it).update(mapOf(
                    "weight" to newweight
                ))
            }
        }
    }
    fun updatedate(newdate:String){
        if (user != null) {
            user.email?.let {
                userdb.document(it).update(mapOf(
                    "birthday" to newdate
                ))
            }
        }
    }
}