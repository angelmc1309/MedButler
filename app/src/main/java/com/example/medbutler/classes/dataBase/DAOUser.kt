package com.example.medbutler.classes.dataBase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.medbutler.classes.view.Login
import com.example.medbutler.classes.view.MainActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Disease
import com.example.medbutler.classes.model.Usuari
import com.example.medbutler.classes.view.Sign_up
import com.google.android.gms.tasks.Task
import com.google.common.io.Files.getFileExtension
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlin.system.exitProcess

class DAOUser : DAO<Usuari> {
    var firebase_auth: FirebaseAuth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()
    var userdb = db.collection("users")
    val user = FirebaseAuth.getInstance().currentUser
    var diseasedb=db.collection("newdisease")
    var imgStore=FirebaseStorage.getInstance().reference.child("admin")
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
    fun save(context: Sign_up, obj: Usuari, password:String) {
        obj.getusername()?.let {
            firebase_auth.createUserWithEmailAndPassword(it, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        obj.getusername()?.let { userdb.document(it).set(obj) }
                        val intent= Intent(context, Login::class.java)
                        context.startActivity(intent)
                    }else{
                        var e: FirebaseAuthException = task.exception as FirebaseAuthException
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun saveUserAll(){
        userdb.document(MainController.getcurrent().getusername()).set(MainController.getcurrent())
    }

    override fun update(obj: Usuari, params: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun login(context: Login, username:String, password: String) {
        this.firebase_auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    MainController.initFirestore()
                    Handler().postDelayed(
                        {
                            val intent= Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        },
                        1000 // value in milliseconds
                    )
                } else {
                    var e: FirebaseAuthException = task.exception as FirebaseAuthException
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
    }
    fun signOut(){
        MainController.user = Usuari()
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
    fun afegirNewDisease(disease: Disease){
        diseasedb.document(disease.id).set(disease)
    }
    fun uploadImage(filename:String,url:Uri){
       /* var fileRef=imgStore!!.child(filename+"."+getFileExtension(url)).putFile(url).
                addOnSuccessListener { taskSnapshot ->
                    val name=taskSnapshot.metadata!!.name
                    val furl=taskSnapshot.downloadUrl
                    tvFileLoad.text = taskSnapshot.metadata!!.path + " - " + taskSnapshot.metadata!!.sizeBytes / 1024 + " KBs
                }.addOnProgressListener { taskSnapshot ->
            // progress percentage
            val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount

            // percentage in progress
            val intProgress = progress.toInt()
            tvFileLoad.text = "Uploaded " + intProgress + "%..."
        }
            .addOnPausedListener { System.out.println("Upload is paused!") }*/
    }
}