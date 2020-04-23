package com.example.medbutler

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*
import com.example.medbutler.classes.controller.*
import android.R.attr
import android.app.Dialog
import android.content.SharedPreferences
import android.view.View
import androidx.fragment.app.DialogFragment

class SettingsAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_account_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_account, AccountSettingsFragment())
            .commit()
    }

    class AccountSettingsFragment : PreferenceFragmentCompat(),
        Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener { //SharedPreferences.OnSharedPreferenceChangeListener
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.account_preferences, rootKey)
            bindPreferenceSummaryToValue(findPreference(getString(R.string.sync_interval)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_sync_connection_type)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.full_name)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.email_adress)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.password)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.height)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.weight)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.date_of_birth)))

            findPreference(getString(R.string.date_of_birth)).onPreferenceClickListener = this
            findPreference(getString(R.string.key_logout)).onPreferenceClickListener = this
            findPreference(getString(R.string.key_delete_account)).onPreferenceClickListener = this

            //bindSharedPreferenceToValue(activity!!.getSharedPreferences(getString(R.string.date_of_birth),0),getString(R.string.date_of_birth))
        }

        /*

        private fun bindSharedPreferenceToValue(sharedPreferences:SharedPreferences, key: String){
            sharedPreferences.registerOnSharedPreferenceChangeListener(this)

            onSharedPreferenceChanged(sharedPreferences,key)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?
        ) {
            if (key.equals("date_of_birth")){
                val newValue = sharedPreferences!!.getString(key, "12")
                val stringValue = newValue.toString()
                findPreference(getString(R.string.date_of_birth))?.summary = stringValue
            }
        }

         */

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

            if (preference?.key.equals("date_of_birth")){
                MainController.changeBirthday(stringValue)
            }else if (preference?.key.equals("full_name")) {
                MainController.changeFullname(stringValue)
            }else if (preference?.key.equals("email_adress")) {
                MainController.changeEmail(stringValue)
            }else if (preference?.key.equals("password")) {
                MainController.changePassword(stringValue)
            }else if (preference?.key.equals("height")) {
                MainController.changeHeight(stringValue)
            }else if (preference?.key.equals("weight")) {
                MainController.changeWeight(stringValue)
            }

            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPreferenceClick(preference: Preference?): Boolean {
            if (preference?.key.equals("date_of_birth")){
                showDatePickerDialog(preference)
            }else if (preference?.key.equals("key_logout")) {
                MainController.signOut()
            }else if (preference?.key.equals("key_delete_account")) {
                MainController.deleteUser(MainController.getcurrent().getusername())
            }
            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        private fun showDatePickerDialog(preference: Preference?) {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener {view: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // +1 because January is zero
                val selectedDate = dayOfMonth.toString() + " / " + (month + 1) + " / " + year
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString(preference?.key, selectedDate).apply()
                preference?.summary = selectedDate
            }, year, month, day)
            dpd.show()
        }

        /*

        class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

            private var listener: DatePickerDialog.OnDateSetListener? = null

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                // Use the current date as the default date in the picker
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                // Create a new instance of DatePickerDialog and return it
                return DatePickerDialog(activity!!, this, year, month, day)
            }

            companion object {
                fun newInstance(listener: DatePickerDialog.OnDateSetListener): DatePickerFragment {
                    val fragment = DatePickerFragment()
                    fragment.listener = listener
                    return fragment
                }
            }

            override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
                // Do something with the date chosen by the user
            }

        }

         */
    }
}