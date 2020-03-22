package com.example.medbutler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.CalendarView

class Calendar : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.calendar_layout)
        val cal = findViewById<CalendarView>(R.id.calendar_view)
        cal.setOnDateChangeListener(CalendarView.OnDateChangeListener { view, year, month, dayOfMonth -> calendarClick(cal)})
    }
    fun actionUser(view: View){
        val intent= Intent(this, UserProfile::class.java)
        startActivity(intent)
    }
    fun calendarClick(view: View){
        val intent= Intent(this, DayActivity::class.java)
        startActivity(intent)

    }
}