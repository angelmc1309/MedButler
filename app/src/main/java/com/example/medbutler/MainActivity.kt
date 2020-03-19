package com.example.medbutler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
    }

    fun viewCalendar(view: View){
        val intent= Intent(this, calendar::class.java)
        startActivity(intent)
    }
}
