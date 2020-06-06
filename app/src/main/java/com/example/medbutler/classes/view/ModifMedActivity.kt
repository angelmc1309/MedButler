package com.example.medbutler.classes.view

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Med
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_modif_med_layout.*
import java.text.SimpleDateFormat
import java.util.Calendar

class ModifMedActivity: AppCompatActivity() {

    lateinit var optionFrequency: Spinner
    lateinit var optionDuration: Spinner
    var startTimeMinute: Int = -1
    var startTimeHour: Int = -1
    var resultFrequency:Int = -1
    var resultDuration:Int = -1
    lateinit var extraObjectId:String

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modif_med_layout)
        val extraObject:Med = intent.extras!!.get("extra_object_med") as Med
        extraObjectId = extraObject.id
        optionFrequency = findViewById(R.id.modifSpinnerFrequency) as Spinner
        optionDuration = findViewById(R.id.modifSpinnerDuration) as Spinner

        val optionsFrequency = arrayOf("  every 4 hours", "  every 6 hours", "  every 12 hours", "  every 24 hours", "  every 48 hours", "  every 72 hours")
        val optionsDuration = arrayOf("just one day", "one week", "two weeks", "one month", "three months", "forever")
        optionFrequency.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsFrequency)
        optionDuration.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsDuration)

        modifMedName.setText(extraObject.getname())
        if (extraObject.getperiod() == 4){
            optionFrequency.setSelection(0)
            resultFrequency = 4
        } else if (extraObject.getperiod() == 6){
            optionFrequency.setSelection(1)
            resultFrequency = 6
        } else if (extraObject.getperiod() == 12){
            optionFrequency.setSelection(2)
            resultFrequency = 12
        } else if (extraObject.getperiod() == 24){
            optionFrequency.setSelection(3)
            resultFrequency = 24
        } else if (extraObject.getperiod() == 48){
            optionFrequency.setSelection(4)
            resultFrequency = 48
        } else if (extraObject.getperiod() == 72){
            optionFrequency.setSelection(5)
            resultFrequency = 72
        }
        if (extraObject.getduration() == 1){
            optionDuration.setSelection(0)
            resultDuration = 1
        } else if (extraObject.getduration() == 7){
            optionDuration.setSelection(1)
            resultDuration = 7
        } else if (extraObject.getduration() == 14){
            optionDuration.setSelection(2)
            resultDuration = 14
        } else if (extraObject.getduration() == 30){
            optionDuration.setSelection(3)
            resultDuration = 30
        } else if (extraObject.getduration() == 90){
            optionDuration.setSelection(4)
            resultDuration = 90
        } else if (extraObject.getduration() == 0){
            optionDuration.setSelection(5)
            resultDuration = 0
        }

        startTimeHour = extraObject.getstartTimeHour()!!
        startTimeMinute = extraObject.getstartTimeMinute()!!
        val formattedHour = String.format("%02d", extraObject.getstartTimeHour())
        val formattedMinute = String.format("%02d", extraObject.getstartTimeMinute())
        modifStartTimeClick.setText(getString(R.string.time_select,formattedHour,formattedMinute))

        modifSwitchNotification.isChecked = extraObject.getallowNotification()!!

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
        modifStartTimeClick.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ view: TimePicker?, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                cal.set(Calendar.MINUTE,minute)
                startTimeHour = SimpleDateFormat("HH").format(cal.time).toInt()
                startTimeMinute = SimpleDateFormat("mm").format(cal.time).toInt()
                modifStartTimeClick.text = SimpleDateFormat("    HH:mm").format(cal.time)
                modifStartTimeClick.error = null
            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }
        modifBarcodeMedNameImage.setOnClickListener{
            val scanner = IntentIntegrator(this)
            scanner.initiateScan()
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
        val backgroundLay: RelativeLayout = findViewById(R.id.backgroundModifMed)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        var addBut: TextView = findViewById(R.id.modifBtnAddMed)
        var disBut: TextView = findViewById(R.id.modifBtnDiscardMed)

        addBut.setBackgroundTintList(ColorStateList.valueOf(MainController.getcurrent().getappearanceInfo().getbrighterToolbarColor()))
        addBut.setBackgroundResource(R.drawable.rounded_button)
        addBut.setTextColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColorText())
        disBut.setBackgroundTintList(ColorStateList.valueOf(MainController.getcurrent().getappearanceInfo().getbrighterToolbarColor()))
        disBut.setBackgroundResource(R.drawable.rounded_button)
        disBut.setTextColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColorText())
        /*
        addBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        addBut.setTextColor(MainActivity.darkerToolbarColorText)
        disBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        disBut.setTextColor(MainActivity.darkerToolbarColorText)
         */
    }

    fun saveChanges(view: View) {

        if(!modifMedName.text.toString().isEmpty() && resultFrequency != -1 && resultDuration != -1 && startTimeMinute != -1 && startTimeHour != -1){

            MainController.getcurrent().getMedListArray()?.find { it.id.equals(extraObjectId) }?.setname(modifMedName.text.toString())
            MainController.getcurrent().getMedListArray()?.find { it.id.equals(extraObjectId) }?.setperiod(resultFrequency)
            MainController.getcurrent().getMedListArray()?.find { it.id.equals(extraObjectId) }?.setduration(resultDuration)
            MainController.getcurrent().getMedListArray()?.find { it.id.equals(extraObjectId) }?.setstartTimeHour(startTimeHour)
            MainController.getcurrent().getMedListArray()?.find { it.id.equals(extraObjectId) }?.setstartTimeMinute(startTimeMinute)
            MainController.getcurrent().getMedListArray()?.find { it.id.equals(extraObjectId) }?.setallowNotification(modifSwitchNotification.isChecked)
            MainController.getcurrent().getMedListArray()?.find { it.id.equals(extraObjectId) }?.id = modifMedName.text.toString()
            MainController.saveUserAll()
            val intent= Intent(this, MedListActivity::class.java)
            startActivity(intent)
        } else if(modifMedName.text.toString().isEmpty()){
            modifMedName.error="Medicine's name empty!!"
            modifMedName.requestFocus()
        } else if(startTimeMinute == -1){
            modifStartTimeClick.error = "Time not selected!!"
            modifStartTimeClick.requestFocus()
        }else if (startTimeHour == -1){
            modifStartTimeClick.error = "Time not selected!!"
            modifStartTimeClick.requestFocus()
        }
    }
    fun discardChanges(view: View) {
        val intent= Intent(this, MedListActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                    modifMedName.setText(result.contents)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}