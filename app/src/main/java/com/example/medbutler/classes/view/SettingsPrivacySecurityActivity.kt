package com.example.medbutler.classes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.example.medbutler.R

class SettingsPrivacySecurityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_privacy_security_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.settings_privacy_security,
                PrivacySecuritySettingsFragment()
            )
            .commit()
    }

    class PrivacySecuritySettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.privacy_security_preferences, rootKey)
        }
    }
}