package com.example.medbutler.classes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Day
import kotlinx.android.synthetic.main.calendar_layout.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Calendar : AppCompatActivity(){

    var selectedDate:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_layout)
        val cal = findViewById<CalendarView>(R.id.calendar_view)
        selectedDate = getDateString()
        cal.setOnDateChangeListener({ view, year, month, dayOfMonth ->
            //Note that months are indexed from 0. So, 0 means january, 1 means February, 2 means march etc.
            val formattedDay = String.format("%02d", dayOfMonth)
            val formattedMonth = String.format("%02d", (month+1))
            val newDate = formattedDay + "/" + formattedMonth + "/" + year.toString()
            if (selectedDate.equals(newDate)){
                calendarClick(cal)
            }else{
                selectedDate = newDate
            }
        })

        updateAppearance()
    }

    override fun onResume() {
        updateAppearance()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gohome_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_gohome -> actiongoHome()
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateAppearance(){
        // background
        val backgroundLay: LinearLayout = findViewById(R.id.backgroundLayout)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        var toolbar: LinearLayout = findViewById(R.id.my_toolbar)
        var userBut: ImageButton = findViewById(R.id.userButton)
        var calendarButt: ImageButton = findViewById(R.id.calendarButton)
        var settingsBut: ImageButton = findViewById(R.id.settingsButton)
        var medsBut: ImageButton = findViewById(R.id.medsButton)
        toolbar.setBackgroundColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColor())
        userBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        calendarButt.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        settingsBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        medsBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
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
        val intent= Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
    fun calendarClick(view: View){
        val intentDay= Intent(this, DayActivity::class.java)
        val day: Day
        if (!MainController.existsThisDay(selectedDate)){
            MainController.createDay(selectedDate)
        }
        day = MainController.getcurrent().calendar.find(selectedDate)!!
        intentDay.putExtra("extra_object_day", day as Serializable)
        startActivity(intentDay)
    }
    fun actiongoHome(){
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun getDateString(): String{
        val date = SimpleDateFormat("dd/MM/yyyy")
        val selectedDate: String = date.format(Date(calendar_view.getDate()))

        return selectedDate
    }

}