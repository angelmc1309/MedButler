package com.example.medbutler.classes.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController

class DayActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.day_layout)
        updateAppearance()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        updateAppearance()
        super.onResume()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun updateAppearance(){
        // background
        val backgroundLay: ConstraintLayout = findViewById(R.id.dayConstraintLayout)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gohome_calendar_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_gocalendar -> { actiongoCalendar()}
            R.id.action_gohome -> { actiongoHome()}
        }
        return super.onOptionsItemSelected(item)
    }

    fun actionAddReminder(view: View) {
        val intent= Intent(this, AddReminderActivity::class.java)
        startActivity(intent)
        //Toast.makeText(this,"Functionality Add REMINDER not implemented yet",Toast.LENGTH_LONG).show()
    }
    fun actionAddTask(view: View) {
        val intent= Intent(this, AddTaskActivity::class.java)
        startActivity(intent)
        //Toast.makeText(this,"Functionality Add TASK not implemented yet",Toast.LENGTH_LONG).show()
    }
    fun actionAddFood(view: View) {
        val intent= Intent(this, AddFoodActivity::class.java)
        startActivity(intent)
        //Toast.makeText(this,"Functionality Add FOOD not implemented yet",Toast.LENGTH_LONG).show()
    }
    fun actiongoCalendar(){
        val intent= Intent(this, Calendar::class.java)
        startActivity(intent)
    }
    fun actiongoHome(){
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}