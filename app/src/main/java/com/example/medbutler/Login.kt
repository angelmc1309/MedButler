package com.example.medbutler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Usuari
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.runBlocking

class Login : AppCompatActivity() {
    private lateinit var firebase_auth: FirebaseAuth;
    val controller:MainController=MainController()
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebase_auth= FirebaseAuth.getInstance()

      //  Toast.makeText(this,controller.getuu().getusername(), Toast.LENGTH_LONG).show()
    }

    fun actionLog(view: View){
        if(usernameLogin.text.toString().isEmpty()){
            usernameLogin.error="Username empty!!"
            usernameLogin.requestFocus()
            return
        }
        if(passwordLogin.text.toString().isEmpty()){
            passwordLogin.error="Password empty!"
            usernameLogin.requestFocus()
            return

        }
        if (!usernameLogin.text.toString().isEmpty() && !passwordLogin.text.toString().isEmpty()){
            this.firebase_auth.signInWithEmailAndPassword(usernameLogin.text.toString(),passwordLogin.text.toString()).addOnCompleteListener(this){
                task->
                if(task.isSuccessful){

                    //Toast.makeText(this,controller.getCurrentUserInfo(), Toast.LENGTH_LONG).show()
                    Toast.makeText(this,"hola!", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    var e: FirebaseAuthException = task.exception as FirebaseAuthException
                    Toast.makeText(baseContext,e.message.toString(),Toast.LENGTH_LONG).show()
                }
            }

        }else{
            Toast.makeText(this,"Usuari/contrasenya incorrecte!", Toast.LENGTH_SHORT).show()
        }

    }
    fun actionRegistre(view:View){
        val intent=Intent(this, Sign_up::class.java)
        startActivity(intent)
    }

}

