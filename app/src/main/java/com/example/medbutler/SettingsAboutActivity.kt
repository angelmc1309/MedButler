package com.example.medbutler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat

class SettingsAboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_about_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_about, AccountSettingsFragment())
            .commit()
    }

    class AccountSettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.about_preferences, rootKey)
        }
    }
}