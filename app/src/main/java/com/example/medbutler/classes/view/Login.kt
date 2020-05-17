package com.example.medbutler.classes.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login: TextView = findViewById(R.id.butLogin)
        val register: TextView = findViewById(R.id.newUserLabelLogin)

        login.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.grey_clear)))
        login.setBackgroundResource(R.drawable.rounded_button)
        //addBut.setTextColor()

        register.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.grey_transp)))
        register.setBackgroundResource(R.drawable.rounded_button)
        //addBut.setTextColor()
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

