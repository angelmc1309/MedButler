package com.example.medbutler.classes.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController

class SettingsNotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_notifications_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.settings_notifications,
                NotificationsSettingsFragment()
            )
            .commit()

    }

    class NotificationsSettingsFragment : PreferenceFragmentCompat(),
        Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.notifications_preferences, rootKey)

            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_notification_ringtone)))
            

        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = this

            onPreferenceChange(
                preference,
                PreferenceManager
                    .getDefaultSharedPreferences(preference.context)
                    .getString(preference.key, "")
            )
        }

        override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {
            val stringValue = value.toString()

            if (preference is ListPreference) {
                val listPreference = preference
                val prefIndex = listPreference.findIndexOfValue(stringValue)
                if (prefIndex >= 0) { // if (prefIndex >= 0) {
                    preference.setSummary(listPreference.entries[prefIndex])
                } // else preference.setSummary null ??
            } else if (preference is EditTextPreference) {
                preference?.summary = stringValue
            } else if (preference is SwitchPreferenceCompat) {

                MainController.setNotificationAllowed(preference.isChecked)

            }
            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPreferenceClick(preference: Preference?): Boolean {
            if (preference is SwitchPreferenceCompat) {


                MainController.setNotificationAllowed(preference.isChecked)
            }
            return true
        }

    }
}