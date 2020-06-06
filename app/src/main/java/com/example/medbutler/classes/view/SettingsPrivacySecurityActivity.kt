package com.example.medbutler.classes.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
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

    class PrivacySecuritySettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.privacy_security_preferences, rootKey)

            findPreference(getString(R.string.key_privacy_policy)).onPreferenceClickListener = this
            findPreference(getString(R.string.key_terms_conditions)).onPreferenceClickListener = this
        }

        override fun onPreferenceClick(preference: Preference?): Boolean {
            if (preference?.key.equals("key_privacy_policy")){
                openDialogPrivacyPolicy();
            } else if (preference?.key.equals("key_terms_conditions")){
                openDialogTermsConditions();
            }
            return true
        }

        private fun openDialogPrivacyPolicy() {
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@PrivacySecuritySettingsFragment.activity!!)

            // Set the alert dialog title
            builder.setTitle("Privacy Policy")

            // Display a message on alert dialog
            builder.setMessage(getText(R.string.privacy_policy))

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Close"){dialog, which ->
                // Do something when user press the positive button

            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }

        private fun openDialogTermsConditions() {
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@PrivacySecuritySettingsFragment.activity!!)

            // Set the alert dialog title
            builder.setTitle("Terms & Conditions")

            // Display a message on alert dialog
            builder.setMessage(getText(R.string.terms_and_conditions))

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