package com.example.medbutler

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*
import com.example.medbutler.ListView.*
import kotlinx.android.synthetic.*
import petrov.kristiyan.colorpicker.ColorPicker
import petrov.kristiyan.colorpicker.ColorPicker.OnChooseColorListener
import com.example.medbutler.classes.controller.MainController


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
            bindPreferenceSummaryToValue(findPreference(getString(R.string.change_color_toolbar)))

            val switch:SwitchPreferenceCompat = findPreference(getString(R.string.change_color_toolbar)) as SwitchPreferenceCompat
            switch.isChecked = MainController.getUsuariPerProvarEdu().getappearanceInfo().getchangeToolbarWithColorTheme()
            findPreference(getString(R.string.key_color_theme)).onPreferenceClickListener = this
            findPreference(getString(R.string.profile_picture_ListPref)).onPreferenceClickListener = this
            findPreference(getString(R.string.treatment_picture_ListPref)).onPreferenceClickListener = this
            findPreference(getString(R.string.calendar_picture_ListPref)).onPreferenceClickListener = this
            findPreference(getString(R.string.settings_picture_ListPref)).onPreferenceClickListener = this
            findPreference(getString(R.string.key_background_theme)).onPreferenceClickListener = this
            findPreference(getString(R.string.key_toolbar_color)).onPreferenceClickListener = this
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = this
        }

        override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {

            if (preference is SwitchPreferenceCompat) {
                if (!preference.isChecked){
                    MainController.getUsuariPerProvarEdu().getappearanceInfo().setchangeToolbarWithColorTheme(true)
                    MainController.getUsuariPerProvarEdu().getappearanceInfo().settoolbarColor(MainController.getUsuariPerProvarEdu().getappearanceInfo().getcolorTheme())
                    MainController.getUsuariPerProvarEdu().getappearanceInfo().setdarkerToolbarColor(manipulateColor(MainController.getUsuariPerProvarEdu().getappearanceInfo().getcolorTheme(), 0.7f))
                    MainController.getUsuariPerProvarEdu().getappearanceInfo().setbrighterToolbarColor(manipulateColor(MainController.getUsuariPerProvarEdu().getappearanceInfo().getcolorTheme(), 1.3f))
                    MainController.getUsuariPerProvarEdu().getappearanceInfo().setdarkerToolbarColorText(manipulateColor(MainController.getUsuariPerProvarEdu().getappearanceInfo().getcolorTheme(), 0.4f))
                }else{
                    MainController.getUsuariPerProvarEdu().getappearanceInfo().setchangeToolbarWithColorTheme(false)
                }
            }
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
            }else if (preference?.key.equals("key_background_theme")) {
                if (preference != null) {
                    val intent = Intent(context, ModelListViewBackground::class.java)
                    startActivity(intent)
                }
            }else if (preference?.key.equals("key_toolbar_color")) {
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
                    if (preference?.key.equals("key_color_theme")){
                        // val colorBrighter:Int = manipulateColor(color, 0.85f)
                        MainController.getUsuariPerProvarEdu().getappearanceInfo().setcolorTheme(color)
                        if (MainController.getUsuariPerProvarEdu().getappearanceInfo().getchangeToolbarWithColorTheme()){
                            MainController.getUsuariPerProvarEdu().getappearanceInfo().settoolbarColor(color)
                            MainController.getUsuariPerProvarEdu().getappearanceInfo().setdarkerToolbarColor(manipulateColor(color, 0.7f))
                            MainController.getUsuariPerProvarEdu().getappearanceInfo().setbrighterToolbarColor(manipulateColor(color, 1.3f))
                            MainController.getUsuariPerProvarEdu().getappearanceInfo().setdarkerToolbarColorText(manipulateColor(color, 0.4f))
                        }
                    }else if (preference?.key.equals("key_toolbar_color")){
                        MainController.getUsuariPerProvarEdu().getappearanceInfo().settoolbarColor(color)
                        MainController.getUsuariPerProvarEdu().getappearanceInfo().setdarkerToolbarColor(manipulateColor(color, 0.7f))
                        MainController.getUsuariPerProvarEdu().getappearanceInfo().setbrighterToolbarColor(manipulateColor(color, 1.3f))
                        MainController.getUsuariPerProvarEdu().getappearanceInfo().setdarkerToolbarColorText(manipulateColor(color, 0.4f))
                    }
                }

                override fun onCancel() {
                    // put code
                }
            })
        }

        // Use a factor less than 1.0f to darken.
        fun manipulateColor(color: Int, factor: Float): Int {
            val a: Int = Color.alpha(color)
            val r = Math.round(Color.red(color) * factor).toInt()
            val g = Math.round(Color.green(color) * factor).toInt()
            val b = Math.round(Color.blue(color) * factor).toInt()
            return Color.argb(
                a,
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255)
            )
        }
    }
}
