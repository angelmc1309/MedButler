package com.example.medbutler

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout

class UserProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_layout)
        updateAppearance()

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
        val idBack = resources.getIdentifier(MainActivity.background, "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        var toolbar:LinearLayout = findViewById(R.id.my_toolbar)
        var userBut:ImageButton = findViewById(R.id.userButton)
        var calendarButt:ImageButton = findViewById(R.id.calendarButton)
        var settingsBut:ImageButton = findViewById(R.id.settingsButton)
        var medsBut:ImageButton = findViewById(R.id.medsButton)
        toolbar.setBackgroundColor(MainActivity.darkerToolbarColor)
        userBut.setBackgroundColor(MainActivity.toolbarColor)
        calendarButt.setBackgroundColor(MainActivity.toolbarColor)
        settingsBut.setBackgroundColor(MainActivity.toolbarColor)
        medsBut.setBackgroundColor(MainActivity.toolbarColor)
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
}
