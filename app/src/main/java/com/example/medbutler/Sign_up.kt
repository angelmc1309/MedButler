package com.example.medbutler

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*
import java.util.Calendar

class Sign_up : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }
    fun actionGoToLogin(view: View){
        val intent= Intent(this, login::class.java)
        startActivity(intent)
    }
    fun pickBirthday(view: View){
        val calendari=java.util.Calendar.getInstance()
        val any=calendari.get(java.util.Calendar.YEAR)
        val mes=calendari.get(java.util.Calendar.MONTH)
        val dia=calendari.get(Calendar.DAY_OF_MONTH)
        val mostrarDate=DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, lany, lmes, ldia -> birthdayRegist.setText(""+ldia+"/"+(lmes.toInt()+1).toString()+"/"+lany)} , any, mes, dia)
        mostrarDate.show()
    }
    fun enregistrar(view: View){
        Toast.makeText(this,"Encara no pots enregistrar!", Toast.LENGTH_SHORT).show()
        val intent= Intent(this, login::class.java)
        startActivity(intent)


    }

}




















