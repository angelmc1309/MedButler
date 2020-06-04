package com.example.medbutler.classes.dataBase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Disease
import com.example.medbutler.classes.model.Usuari
import com.example.medbutler.classes.view.*
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class DAOUser : DAO<Usuari> {
    var firebase_auth: FirebaseAuth = FirebaseAuth.getInstance()

    var db = FirebaseFirestore.getInstance()
    var userdb = db.collection("users")
    var imgdb = db.collection("img")
    val user = FirebaseAuth.getInstance().currentUser
    var diseasedb=db.collection("newdisease")

     val PICK_IMAGE_REQUEST = 71
     var filePath: Uri? = null
     var firebaseStore: FirebaseStorage? = FirebaseStorage.getInstance()
     var storageReference: StorageReference? = FirebaseStorage.getInstance().reference

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
        obj.getusername().let {
            firebase_auth.createUserWithEmailAndPassword(it, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        obj.getusername().let { userdb.document(it).set(obj) }
                     //   android:src="@drawable/user_angel"
                      //  val uri = Uri.parse("android.resource://app/drawable-v24/user_angel")

                     //   uploadImg(uri)
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
        //To change body of created functions use File | Settings | File Templates.
    }
    fun login(context: Login, username:String, password: String) {
        this.firebase_auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    MainController.initFirestore()
                    MainController.initFAQFacade()
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
    fun launchGallery(context: UserProfile){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        context.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }
     fun addUploadRecordToDb(uri: String, context: Context){
        val data = HashMap<String, Any>()
        data["imageUrl"] = uri

         firebase_auth.currentUser?.email?.let {
             imgdb.document(it).set(data)
                 .addOnSuccessListener { documentReference ->
                     Toast.makeText(context, "Saved to DB", Toast.LENGTH_LONG).show()
                 }
                 .addOnFailureListener { e ->
                     Toast.makeText(context, "Error saving to DB", Toast.LENGTH_LONG).show()
                 }
         }
    }
    fun loadImg(context: UserProfile){

        var imageref = storageReference?.child("uploads/"+firebase_auth.currentUser!!.email)
        imageref!!.downloadUrl.addOnSuccessListener {Uri->
            val imageURL = Uri.toString()
            var image=context.findViewById<ImageView>(R.id.user_image)
            Glide.with(context)
                .load(imageURL).apply(RequestOptions().override(Target.SIZE_ORIGINAL))
                .into(image)

        }

    }
    fun uploadImg(context:UserProfile,filePath: Uri){
        if(filePath != null){

            val ref = storageReference?.child("uploads/" + firebase_auth.currentUser!!.email)
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener{
                    task ->
                if(task.isSuccessful){
                    changeImgState(true)
                    val downloadUri = task.result
                    addUploadRecordToDb(downloadUri.toString(),context)
                }
            }

        }else{
            Toast.makeText(context, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }
    fun changeImgState(newState:Boolean) {
        if (!MainController.getcurrent().getimgState()) {


            user!!.email?.let {
                userdb.document(it).update(
                    mapOf(
                        "imgState" to newState
                    )
                )
            }
            MainController.getcurrent().setimgState(newState)
        }
    }
}