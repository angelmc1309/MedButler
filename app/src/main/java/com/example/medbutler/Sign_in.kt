package com.example.medbutler

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.util.*

class Sign_in : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }
    fun actionGoToLogin(view: View){
        val intent= Intent(this, login::class.java)
        startActivity(intent)
    }
    fun pickBirthday(view: View){
        val calendari=Calendar.getInstance()
        val any=calendari.get(Calendar.YEAR)
        val mes=calendari.get(Calendar.MONTH)
        val dia=calendari.get(Calendar.DAY_OF_MONTH)
        val mostrarDate=DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, lany, lmes, ldia -> birthdayRegist.setText(""+ldia+"/"+(lmes.toInt()+1).toString()+"/"+lany)} , any, mes, dia)
        mostrarDate.show()
    }

}




















