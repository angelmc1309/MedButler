package com.example.medbutler.classes.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.medbutler.R

class SettingsHelpSupportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_help_support_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.settings_help_support,
                HelpSupportSettingsFragment()
            )
            .commit()
    }

    class HelpSupportSettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.help_support_preferences, rootKey)

            findPreference(getString(R.string.key_faq)).onPreferenceClickListener = this
        }

        override fun onPreferenceClick(preference: Preference?): Boolean {
            if (preference?.key.equals("key_faq")){
                openDialogFAQ();
            }
            return true
        }

        private fun openDialogFAQ() {
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@HelpSupportSettingsFragment.activity!!)

            // Set the alert dialog title
            builder.setTitle("FAQ")

            // Display a message on alert dialog
            builder.setMessage(getText(R.string.faqs_english))

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Close"){dialog, which ->
                // Do something when user press the positive button

            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
    }
}