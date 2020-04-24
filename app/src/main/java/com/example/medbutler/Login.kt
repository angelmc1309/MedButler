package com.example.medbutler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.dataBase.DAOUser
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

       // Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
            MainController.login(this,usernameLogin.text.toString(),passwordLogin.text.toString())
        }

    }
    fun actionRegistre(view:View){
        val intent=Intent(this, Sign_up::class.java)
        startActivity(intent)
    }

}

