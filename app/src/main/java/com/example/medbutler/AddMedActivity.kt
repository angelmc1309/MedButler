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
import com.example.medbutler.classes.controller.*

class AddMedActivity : AppCompatActivity() {

    lateinit var optionFrequency: Spinner
    lateinit var optionDuration: Spinner
    var startTimeMinute: Int = -1
    var startTimeHour: Int = -1
    var resultFrequency:Int = -1
    var resultDuration:Int = -1

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_med_layout)
        optionFrequency = findViewById(R.id.spinnerFrequency) as Spinner
        optionDuration = findViewById(R.id.spinnerDuration) as Spinner

        val optionsFrequency = arrayOf("  every 4 hours", "  every 6 hours", "  every 12 hours", "  every 24 hours", "  every 48 hours", "  every 72 hours")
        val optionsDuration = arrayOf("just one day", "one week", "two weeks", "one month", "three months", "forever")
        optionFrequency.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsFrequency)
        optionDuration.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsDuration)
        optionFrequency.setSelection(3)
        optionDuration.setSelection(5)

        optionFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultFrequency = -1 // Nothing Selected
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                resultFrequency = optionsFrequency.get(position).filter { it.isDigit() }.toInt()
            }
        }
        optionDuration.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultDuration = -1 // Nothing Selected
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0){
                    resultDuration = 1
                } else if (position == 1){
                    resultDuration = 7
                } else if (position == 2){
                    resultDuration = 14
                } else if (position == 3){
                    resultDuration = 30
                } else if (position == 4){
                    resultDuration = 90
                } else if (position == 5){
                    resultDuration = 0
                }
            }
        }
        startTimeClick.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{view: TimePicker?, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                cal.set(Calendar.MINUTE,minute)
                startTimeHour = SimpleDateFormat("HH").format(cal.time).toInt()
                startTimeMinute = SimpleDateFormat("mm").format(cal.time).toInt()
                startTimeClick.text = SimpleDateFormat("    HH:mm").format(cal.time)
                startTimeClick.error = null
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
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        var addBut: TextView = findViewById(R.id.btnAddMed)
        var disBut: TextView = findViewById(R.id.btnDiscardMed)

        addBut.setBackgroundTintList(ColorStateList.valueOf(MainController.getcurrent().getappearanceInfo().getbrighterToolbarColor()))
        addBut.setTextColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColorText())
        disBut.setBackgroundTintList(ColorStateList.valueOf(MainController.getcurrent().getappearanceInfo().getbrighterToolbarColor()))
        disBut.setTextColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColorText())
        /*
        addBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        addBut.setTextColor(MainActivity.darkerToolbarColorText)
        disBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        disBut.setTextColor(MainActivity.darkerToolbarColorText)
         */
    }

    fun addMed(view: View) {

        if(!medName.text.toString().isEmpty() && resultFrequency != -1 && resultDuration != -1 && startTimeMinute != -1 && startTimeHour != -1){
            MainController.getcurrent().addMed(medName.text.toString(),medName.text.toString(),resultFrequency,resultDuration,startTimeMinute,startTimeHour,switchNotification.isChecked)
            MainController.saveListMed()
            val intent= Intent(this, MedListActivity::class.java)
            startActivity(intent)
        } else if(medName.text.toString().isEmpty()){
            medName.error="Medicine's name empty!!"
            medName.requestFocus()
        } else if(startTimeMinute == -1){
            startTimeClick.error = "Time not selected!!"
            startTimeClick.requestFocus()
        }else if (startTimeHour == -1){
            startTimeClick.error = "Time not selected!!"
            startTimeClick.requestFocus()
        }
    }
    fun discardMed(view: View) {
        val intent= Intent(this, MedListActivity::class.java)
        startActivity(intent)
    }
}
