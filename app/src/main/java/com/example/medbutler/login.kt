package com.example.medbutler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun actionLog(view: View){
        var stat= usernameLogin.text.toString().equals("angeljaja@gmail.com") && passwordLogin.text.toString().equals("123456")
        if(stat==true){
            Toast.makeText(this,"hola!", Toast.LENGTH_SHORT).show()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun actionRegistre(view:View){
        val intent=Intent(this, Sign_in::class.java)
        startActivity(intent)
    }

}

