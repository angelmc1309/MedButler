package com.example.medbutler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class UserProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_layout)
    }
    fun actionCalendar(view: View){
        val intent= Intent(this, Calendar::class.java)
        startActivity(intent)
    }
    fun actionUser(view: View){
        val intent= Intent(this, UserProfile::class.java)
        startActivity(intent)
    }
    fun actionMeds(view: View){
        val intent= Intent(this, MedListActivity::class.java)
        startActivity(intent)
    }
    fun actionSettings(view: View){
        //TODO
    }
}
