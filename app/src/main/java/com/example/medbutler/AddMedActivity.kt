package com.example.medbutler

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_add_med_layout.*
import java.text.SimpleDateFormat
import java.util.Calendar
import com.example.medbutler.classes.controller.MainController

class AddMedActivity : AppCompatActivity() {

    lateinit var optionFrequency: Spinner
    lateinit var resultFrequency: TextView
    lateinit var optionDuration: Spinner
    lateinit var resultDuration: TextView

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_med_layout)
        optionFrequency = findViewById(R.id.spinnerFrequency) as Spinner
        resultFrequency = findViewById(R.id.textSpinnerFrequency) as TextView
        optionDuration = findViewById(R.id.spinnerDuration) as Spinner
        resultDuration = findViewById(R.id.textSpinnerDuration) as TextView

        val optionsFrequency = arrayOf("  every 4 hours", "  every 6 hours", "  every 12 hours", "  every 24 hours", "  every 48 hours", "  every 72 hours")
        val optionsDuration = arrayOf("just one day", "one week", "two weeks", "one month", "three months", "forever")
        optionFrequency.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsFrequency)
        optionDuration.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsDuration)
        optionFrequency.setSelection(3)
        optionDuration.setSelection(5)

        optionFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultFrequency.text = "Please Select an Option"
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                resultFrequency.text = optionsFrequency.get(position)
            }
        }
        optionDuration.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultDuration.text = "Please Select an Option"
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                resultDuration.text = optionsDuration.get(position)
            }
        }
        startTimeClick.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{view: TimePicker?, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                cal.set(Calendar.MINUTE,minute)
                startTimeClick.text = SimpleDateFormat("    HH:mm").format(cal.time)
            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }
        updateAppearance()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        updateAppearance()
        super.onResume()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun updateAppearance(){
        // background
        val backgroundLay: RelativeLayout = findViewById(R.id.backgroundAddMed)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getUsuariPerProvarEdu().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        var addBut: TextView = findViewById(R.id.btnAddMed)
        var disBut: TextView = findViewById(R.id.btnDiscardMed)

        addBut.setBackgroundTintList(ColorStateList.valueOf(MainController.getUsuariPerProvarEdu().getappearanceInfo().getbrighterToolbarColor()))
        addBut.setTextColor(MainController.getUsuariPerProvarEdu().getappearanceInfo().getdarkerToolbarColorText())
        disBut.setBackgroundTintList(ColorStateList.valueOf(MainController.getUsuariPerProvarEdu().getappearanceInfo().getbrighterToolbarColor()))
        disBut.setTextColor(MainController.getUsuariPerProvarEdu().getappearanceInfo().getdarkerToolbarColorText())
        /*
        addBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        addBut.setTextColor(MainActivity.darkerToolbarColorText)
        disBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        disBut.setTextColor(MainActivity.darkerToolbarColorText)
         */
    }

    fun addMed(view: View) {
        val intent= Intent(this, MedListActivity::class.java)
        startActivity(intent)
    }
    fun discardMed(view: View) {
        val intent= Intent(this, MedListActivity::class.java)
        startActivity(intent)
    }
}
