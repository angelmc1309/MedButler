package com.example.medbutler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat

class SettingsPrivacySecurityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_privacy_security_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_privacy_security, AccountSettingsFragment())
            .commit()
    }

    class AccountSettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.privacy_security_preferences, rootKey)
        }
    }
}