package com.example.medbutler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        var listview = findViewById<ListView>(R.id.listView)
        var list = mutableListOf<ModelSettings>()

        list.add(ModelSettings("Account", "you can update your personal information", R.drawable.account_icon))
        list.add(ModelSettings("Notifications", "all notifications options", R.drawable.notifications_icon))
        list.add(ModelSettings("Appearance", "customize the look of the application", R.drawable.appearance_icon))
        list.add(ModelSettings("Privacy & Security", "see all privacy & security settings", R.drawable.privacyandsecurity_icon))
        list.add(ModelSettings("Help and Support", "App FAQ, contact us...", R.drawable.helpandsupport_icon))
        list.add(ModelSettings("About", "information about us, actual version of the application", R.drawable.about_icon))

        listview.adapter = AdapterSettings(this, R.layout.row_settings, list)
        listview.setOnItemClickListener {parent:AdapterView<*>, view:View, position:Int, id:Long ->
            if (position == 0){
                Toast.makeText(this@SettingsActivity, "you click on Account", Toast.LENGTH_LONG).show()
                val intent= Intent(this, settAcc)
                startActivity(intent)
            }
            if (position == 1){
                Toast.makeText(this@SettingsActivity, "you click on Notifications", Toast.LENGTH_LONG).show()
                val intent= Intent(this, settNotif)
                startActivity(intent)
            }
            if (position == 2){
                Toast.makeText(this@SettingsActivity, "you click on Appearance", Toast.LENGTH_LONG).show()
                val intent= Intent(this, settAppear)
                startActivity(intent)
            }
            if (position == 3){
                Toast.makeText(this@SettingsActivity, "you click on Privacy & Security", Toast.LENGTH_LONG).show()
                val intent= Intent(this, settPriv)
                startActivity(intent)
            }
            if (position == 4){
                Toast.makeText(this@SettingsActivity, "you click on Help and Support", Toast.LENGTH_LONG).show()
                val intent= Intent(this, settHelpSup)
                startActivity(intent)
            }
            if (position == 5){
                Toast.makeText(this@SettingsActivity, "you click on About", Toast.LENGTH_LONG).show()
                val intent= Intent(this, settAbout)
                startActivity(intent)
            }
        }
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

}
