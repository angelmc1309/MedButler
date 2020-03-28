package com.example.medbutler

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.preference.*
import petrov.kristiyan.colorpicker.ColorPicker
import petrov.kristiyan.colorpicker.ColorPicker.OnChooseColorListener


class SettingsAppearanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_appearence_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_appearence, AppearanceSettingsFragment())
            .commit()
    }

    class AppearanceSettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.appearence_preferences, rootKey)

            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_color_theme)))

            findPreference(getString(R.string.key_color_theme)).onPreferenceClickListener = this
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = this

            onPreferenceChange(preference,
                PreferenceManager
                    .getDefaultSharedPreferences(preference.context)
                    .getString(preference.key, ""))
        }

        override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {

            val stringValue:String
            if(value.toString() != ""){
                stringValue = value.toString()
            }else{
                stringValue = preference?.summary.toString()
            }

            if (preference is ListPreference) {
                val listPreference = preference
                val prefIndex = listPreference.findIndexOfValue(stringValue)
                if (prefIndex >= 0) { // if (prefIndex >= 0) {
                    preference.setSummary(listPreference.entries[prefIndex])
                } // else preference.setSummary null ??
            } else if (preference is EditTextPreference) {
                preference?.summary = stringValue
            } else {
                preference?.summary = stringValue
            }
            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPreferenceClick(preference: Preference?): Boolean {
            if (preference?.key.equals("key_color_theme")){
                showColorPicker(preference)
            }
            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        private fun showColorPicker(preference: Preference?) {
            val ColorsChoice  = resources.getStringArray(R.array.color_choices).toList()
            val ColorsChoiceDarker = resources.getStringArray(R.array.color_choices_700).toList()
            val colorPicker = ColorPicker(activity)
            colorPicker.setColumns(5)
            colorPicker.setColors(R.array.color_choices)
            colorPicker.setTitle("Selecciona un color")
            colorPicker.show()
            colorPicker.setOnChooseColorListener(object : OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                    MainActivity.colorTheme = color
                }

                override fun onCancel() {
                    // put code
                }
            })
        }
    }
}
