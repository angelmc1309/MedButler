package com.example.medbutler.classes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.medbutler.R

class DayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.day_layout)
    }

    fun actionAddReminder(view: View) {
        Toast.makeText(this,"Functionality Add REMINDER not implemented yet",Toast.LENGTH_LONG).show()
    }
    fun actionAddTask(view: View) {
        Toast.makeText(this,"Functionality Add TASK not implemented yet",Toast.LENGTH_LONG).show()
    }
    fun actionAddFood(view: View) {
        Toast.makeText(this,"Functionality Add FOOD not implemented yet",Toast.LENGTH_LONG).show()
    }
}
