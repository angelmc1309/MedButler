package com.example.medbutler

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.medbutler.classes.controller.*

class UserProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_layout)
        updateAppearance()
        initProfile()

    }

    override fun onResume() {
        updateAppearance()
        initProfile()
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
        val backgroundLay:LinearLayout = findViewById(R.id.backgroundLayout)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        var toolbar:LinearLayout = findViewById(R.id.my_toolbar)
        var userBut:ImageButton = findViewById(R.id.userButton)
        var calendarButt:ImageButton = findViewById(R.id.calendarButton)
        var settingsBut:ImageButton = findViewById(R.id.settingsButton)
        var medsBut:ImageButton = findViewById(R.id.medsButton)
        toolbar.setBackgroundColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColor())
        userBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        calendarButt.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        settingsBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        medsBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
    }

    fun actionCalendar(view: View){
        val intent= Intent(this, Calendar::class.java)
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
    fun actiongoHome(){
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun actionModif(view: View) {
        val intent= Intent(this, SettingsAccountActivity::class.java)
        startActivity(intent)
    }
    private fun initProfile() {
        MainController.initUserProfer(this)
    }

}
