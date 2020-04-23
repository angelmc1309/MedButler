package com.example.medbutler

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.medbutler.ListView.AdapterSettings
import com.example.medbutler.ListView.ModelSettings
import com.example.medbutler.classes.controller.MainController

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        var listview = findViewById<ListView>(R.id.listView)
        var list = mutableListOf<ModelSettings>()

        list.add(
            ModelSettings(
                "Account",
                "you can update your personal information",
                R.drawable.account_icon
            )
        )
        list.add(
            ModelSettings(
                "Notifications",
                "all notifications options",
                R.drawable.notifications_icon
            )
        )
        list.add(
            ModelSettings(
                "Appearance",
                "customize the look of the application",
                R.drawable.appearance_icon
            )
        )
        list.add(
            ModelSettings(
                "Privacy & Security",
                "see all privacy & security settings",
                R.drawable.privacyandsecurity_icon
            )
        )
        list.add(
            ModelSettings(
                "Help and Support",
                "App FAQ, contact us...",
                R.drawable.helpandsupport_icon
            )
        )
        list.add(
            ModelSettings(
                "About",
                "information about us, actual version of the application",
                R.drawable.about_icon
            )
        )

        listview.adapter = AdapterSettings(
            this,
            R.layout.row_settings,
            list
        )
        listview.setOnItemClickListener {parent:AdapterView<*>, view:View, position:Int, id:Long ->
            if (position == 0){
                Toast.makeText(this@SettingsActivity, "you click on Account", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, settAcc)
                startActivity(intent)
            }
            if (position == 1){
                Toast.makeText(this@SettingsActivity, "you click on Notifications", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, settNotif)
                startActivity(intent)
            }
            if (position == 2){
                Toast.makeText(this@SettingsActivity, "you click on Appearance", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, settAppear)
                startActivity(intent)
            }
            if (position == 3){
                Toast.makeText(this@SettingsActivity, "you click on Privacy & Security", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, settPriv)
                startActivity(intent)
            }
            if (position == 4){
                Toast.makeText(this@SettingsActivity, "you click on Help and Support", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, settHelpSup)
                startActivity(intent)
            }
            if (position == 5){
                Toast.makeText(this@SettingsActivity, "you click on About", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, settAbout)
                startActivity(intent)
            }
        }
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

    companion object {
        val settAcc = SettingsAccountActivity::class.java
        val settNotif = SettingsNotificationsActivity::class.java
        val settAppear = SettingsAppearanceActivity()::class.java
        val settPriv = SettingsPrivacySecurityActivity::class.java
        val settHelpSup = SettingsHelpSupportActivity::class.java
        val settAbout = SettingsAboutActivity::class.java
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
    fun actiongoHome(){
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
