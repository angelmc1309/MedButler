package com.example.medbutler.classes.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController
import kotlinx.android.synthetic.main.user_profile_layout.*
import java.io.IOException

class SettingsAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_account_activity)
        //Load settings framgent
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.settings_account,
                AccountSettingsFragment()
            )
            .commit()
    }

    class AccountSettingsFragment : PreferenceFragmentCompat(),
        Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener { //SharedPreferences.OnSharedPreferenceChangeListener
        val PICK_IMAGE_REQUEST = 71
        var filePath: Uri? = null
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
            findPreference(getString(R.string.key_select_user_image)).onPreferenceClickListener = this
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
                if (preference?.key.equals("date_of_birth")){
                    MainController.getcurrent().setbirthday(stringValue)
                    MainController.changeBirthday(stringValue)
                }else if (preference?.key.equals("full_name")) {
                    MainController.getcurrent().setfullname(stringValue)
                    MainController.changeFullname(stringValue)
                }else if (preference?.key.equals("email_adress")) {
                    MainController.getcurrent().setusername(stringValue)
                    MainController.changeEmail(stringValue)
                }else if (preference?.key.equals("password")) {
                    MainController.changePassword(stringValue)
                }else if (preference?.key.equals("height")) {
                    MainController.getcurrent().setheight(stringValue)
                    MainController.changeHeight(stringValue)
                }else if (preference?.key.equals("weight")) {
                    MainController.getcurrent().setweight(stringValue)
                    MainController.changeWeight(stringValue)
                }
            }else{
                stringValue = preference?.summary.toString()
            }

            if (preference is ListPreference) {
                val listPreference = preference
                val prefIndex = listPreference.findIndexOfValue(stringValue)
                if (prefIndex >= 0) { // if (prefIndex >= 0) {
                    preference.setSummary(listPreference.entries[prefIndex])
                } // else preference.setSummary null ??
            }

            if (preference is EditTextPreference){
                preference.text = ""
            }

            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPreferenceClick(preference: Preference?): Boolean {
            if (preference?.key.equals("date_of_birth")){
                showDatePickerDialog(preference)
            }else if (preference?.key.equals("key_logout")) {
                MainController.signOut()
                val intent= Intent(context, Login::class.java)
                startActivity(intent)
                onDestroy()
            }else if (preference?.key.equals("key_delete_account")) {
                MainController.deleteUser(MainController.getcurrent().getusername())
                val intent= Intent(context, Login::class.java)
                startActivity(intent)
                onDestroy()
            }else if (preference?.key.equals("key_select_user_image")) {
                if (preference != null) {
                    MainController.launchGallery(context!!)
                }
            }
            return true
        }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
                if(data == null || data.data == null){
                    return
                }

                filePath = data.data
                filePath?.let { MainController.uploadImg(context!!, it) }
                //try {
                //    val bitmap = MediaStore.Images.Media.getBitmap(context!!.contentResolver, filePath)
                //    user_image.setImageBitmap(bitmap)
                //} catch (e: IOException) {
                //    e.printStackTrace()
                //    e.printStackTrace()
                //}
            }
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