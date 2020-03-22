package com.example.medbutler

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.preference.*


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
        Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.account_preferences, rootKey)
            bindPreferenceSummaryToValue(findPreference(getString(R.string.sync_interval)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.full_name)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.username)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.email_adress)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.height)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.weight)))

            findPreference(getString(R.string.date_of_birth)).onPreferenceClickListener = this
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
            } else {
                preference?.summary = stringValue
            }
            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPreferenceClick(preference: Preference?): Boolean {
            showDatePickerDialog(preference)
            return true
        }

        private fun showDatePickerDialog(preference: Preference?) {
            val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                // +1 because January is zero
                val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
                onPreferenceChange(preference,selectedDate)
            })
            newFragment.show(fragmentManager!!, "datePicker")
        }

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
    }
}