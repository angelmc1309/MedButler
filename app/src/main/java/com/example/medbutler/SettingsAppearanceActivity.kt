package com.example.medbutler

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*
import com.example.medbutler.ListView.*
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

            //bindPreferenceSummaryToValue(findPreference(getString(R.string.settings_picture_ListPref)))


            findPreference(getString(R.string.key_color_theme)).onPreferenceClickListener = this
            findPreference(getString(R.string.profile_picture_ListPref)).onPreferenceClickListener = this
            findPreference(getString(R.string.treatment_picture_ListPref)).onPreferenceClickListener = this
            findPreference(getString(R.string.calendar_picture_ListPref)).onPreferenceClickListener = this
            findPreference(getString(R.string.settings_picture_ListPref)).onPreferenceClickListener = this
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = this

            onPreferenceChange(preference,
                PreferenceManager
                    .getDefaultSharedPreferences(preference.context)
                    .getString(preference.key, ""))
        }

        override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {

            /*if (preference?.key.equals("profile_picture_ListPref")) {
                if (preference != null) {
                    if ((preference as ListPreference).value.equals("man_profile_image")){
                        MainActivity.profileImagePersonId = "man_profile_image"
                        MainActivity.profileImageBackgroundId = "man_profile_background"
                    } else if (preference.value.equals("woman_profile_image")){
                        MainActivity.profileImagePersonId = "woman_profile_image"
                        MainActivity.profileImageBackgroundId = "woman_profile_background"
                    } else if (preference.value.equals("man_profile_image2")){
                        MainActivity.profileImagePersonId = "man_profile_image2"
                        MainActivity.profileImageBackgroundId = "man_profile_background2"
                    } else if (preference.value.equals("woman_profile_image2")){
                        MainActivity.profileImagePersonId = "woman_profile_image2"
                        MainActivity.profileImageBackgroundId = "woman_profile_background2"
                    } else if (preference.value.equals("man_profile_image3")){
                        MainActivity.profileImagePersonId = "man_profile_image3"
                        MainActivity.profileImageBackgroundId = "man_profile_background3"
                    } else if (preference.value.equals("woman_profile_image3")){
                        MainActivity.profileImagePersonId = "woman_profile_image3"
                        MainActivity.profileImageBackgroundId = "woman_profile_background3"
                    }
                }*/
            /*if (preference?.key.equals("calendar_picture_ListPref")) {
                if (preference != null) {
                    if ((preference as ListPreference).value.equals("cal_im")){
                        MainActivity.calendarImageId = "cal_im"
                        MainActivity.calendarImageBackgroundId = "cal_back"
                    } else if (preference.value.equals("cal2_im")){
                        MainActivity.calendarImageId = "cal2_im"
                        MainActivity.calendarImageBackgroundId = "cal2_back"
                    } else if (preference.value.equals("cal3_im")){
                        MainActivity.calendarImageId = "cal3_im"
                        MainActivity.calendarImageBackgroundId = "cal3_back"
                    } else if (preference.value.equals("cal4_im")){
                        MainActivity.calendarImageId = "cal4_im"
                        MainActivity.calendarImageBackgroundId = "cal4_back"
                    } else if (preference.value.equals("cal5_im")){
                        MainActivity.calendarImageId = "cal5_im"
                        MainActivity.calendarImageBackgroundId = "cal5_back"
                    }
                }*/
            /*if (preference?.key.equals("settings_picture_ListPref")){
                if (preference != null) {
                    if ((preference as ListPreference).value.equals("set1_im")){
                        MainActivity.settingsImageId = "set1_im"
                        MainActivity.settingsImageBackgroundId = "set1_gray"
                    } else if (preference.value.equals("set2_im")){
                        MainActivity.settingsImageId = "set2_im"
                        MainActivity.settingsImageBackgroundId = "set2_gray"
                    } else if (preference.value.equals("set3_im")){
                        MainActivity.settingsImageId = "set3_im"
                        MainActivity.settingsImageBackgroundId = "set3_gray"
                    } else if (preference.value.equals("set4_im")){
                        MainActivity.settingsImageId = "set4_im"
                        MainActivity.settingsImageBackgroundId = "set4_gray"
                    }
                }
            } else if ((preference?.key.equals("treatment_picture_ListPref"))){
                    if ((preference as ListPreference).value.equals("treat1_1")){
                        MainActivity.treatmentImageFirstId = "treat1_1"
                        MainActivity.treatmentImageSecondId = "treat1_2"
                    } else if (preference.value.equals("treat2_1")){
                        MainActivity.treatmentImageFirstId = "treat2_1"
                        MainActivity.treatmentImageSecondId = "treat2_2"
                    } else if (preference.value.equals("treat3_1")){
                        MainActivity.treatmentImageFirstId = "treat3_1"
                        MainActivity.treatmentImageSecondId = "treat3_2"
                    } else if (preference.value.equals("treat3_3")){
                    MainActivity.treatmentImageFirstId = "treat3_3"
                    MainActivity.treatmentImageSecondId = "treat3_2"
                    }
                }
            }*/
            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPreferenceClick(preference: Preference?): Boolean {
            if (preference?.key.equals("key_color_theme")){
                showColorPicker(preference)
            }else if ((preference?.key.equals("treatment_picture_ListPref"))){
                if (preference != null) {
                    val intent= Intent(context, ModelListViewTreatment::class.java)
                    startActivity(intent)
                }
            }else if (preference?.key.equals("profile_picture_ListPref")) {
                if (preference != null) {
                    val intent = Intent(context, ModelListViewProfile::class.java)
                    startActivity(intent)
                }
            }else if (preference?.key.equals("calendar_picture_ListPref")) {
                if (preference != null) {
                    val intent = Intent(context, ModelListViewCalendar::class.java)
                    startActivity(intent)
                }
            }else if (preference?.key.equals("settings_picture_ListPref")) {
                if (preference != null) {
                    val intent = Intent(context, ModelListViewSettings::class.java)
                    startActivity(intent)
                }
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
