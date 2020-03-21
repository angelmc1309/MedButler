package com.example.medbutler

import android.os.Bundle
import android.preference.RingtonePreference
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, MainSettingsFragment())
            .commit()
    }

    class MainSettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener{
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.preferences, rootKey)
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_full_name)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_email)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_sleep_timer)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_music_quality)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_notification_ringtone)))
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = this

            onPreferenceChange(preference,
                PreferenceManager
                    .getDefaultSharedPreferences(preference.context)
                    .getString(preference.key, ""))
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
            }
            return true
        }
    }
}