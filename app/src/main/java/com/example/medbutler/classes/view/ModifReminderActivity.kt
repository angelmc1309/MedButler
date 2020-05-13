package com.example.medbutler.classes.view

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.medbutler.R
import com.example.medbutler.classes.controller.*
import com.example.medbutler.classes.model.Day
import com.example.medbutler.classes.model.Reminder
import kotlinx.android.synthetic.main.activity_modif_reminder_layout.*
import java.io.Serializable
import kotlin.random.Random

class ModifReminderActivity : AppCompatActivity() {

    lateinit var optionImportance: Spinner
    var resultImportance:Int = -1
    lateinit var extraObjectDayId:String

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modif_reminder_layout)
        val extraObject: Reminder = intent.extras!!.get("extra_object_reminder") as Reminder
        extraObjectDayId = extraObject.getreminderDate()
        optionImportance = findViewById(R.id.spinnerImportanceModify) as Spinner

        /*
        Importance -->  0 = indiferente/indifferent
                        1 = opcional/optional
                        2 = importante/important
                        3 = Muy importante/Very important
        */

        val optionsImportance = arrayOf("indifferent", "optional", "important", "very important")
        optionImportance.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsImportance)

        reminderNameModify.setText(extraObject.getreminderName())
        if (extraObject.getimportance() == 0){
            optionImportance.setSelection(0)
            resultImportance = 0
        } else if (extraObject.getimportance() == 1){
            optionImportance.setSelection(1)
            resultImportance = 1
        } else if (extraObject.getimportance() == 2){
            optionImportance.setSelection(2)
            resultImportance = 2
        } else if (extraObject.getimportance() == 3){
            optionImportance.setSelection(3)
            resultImportance = 3
        }

        switchNotificationReminderModify.isChecked = extraObject.getallowNotification()!!

        optionImportance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultImportance = -1 // Nothing Selected
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                resultImportance = position
            }
        }
        createbackground()
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
        val backgroundLay: RelativeLayout = findViewById(R.id.backgroundModifyReminder)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        val addBut: TextView = findViewById(R.id.btnModifyReminder)
        val disBut: TextView = findViewById(R.id.btnDiscardModifyReminder)

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

    fun actionModifyReminder(view: View) {

        if(!reminderNameModify.text.toString().isEmpty() && resultImportance != -1){

            val day: Day = MainController.getcurrent().calendar.find(extraObjectDayId)!!
            day.getReminderArray()?.find { it.id.equals(extraObjectDayId) }?.setreminderName(reminderNameModify.text.toString())
            day.getReminderArray()?.find { it.id.equals(extraObjectDayId) }?.setimportance(resultImportance)
            day.getReminderArray()?.find { it.id.equals(extraObjectDayId) }?.setallowNotification(switchNotificationReminderModify.isChecked)
            day.getReminderArray()?.find { it.id.equals(extraObjectDayId) }?.id = reminderNameModify.text.toString()
            MainController.saveUserAll()

            val intent= Intent(this, DayActivity::class.java)
            intent.putExtra("extra_object_day", day as Serializable)
            startActivity(intent)
        } else if(reminderNameModify.text.toString().isEmpty()){
            reminderNameModify.error="Reminder's name empty!!"
            reminderNameModify.requestFocus()
        }
    }

    fun actionDiscardModifyReminder(view: View) {
        val intent= Intent(this, DayActivity::class.java)
        val day: Day = MainController.getcurrent().calendar.find(extraObjectDayId)!!
        intent.putExtra("extra_object_day", day as Serializable)
        startActivity(intent)
    }

    fun createbackground(){
        val numbers = mutableListOf<Int>()
        var num:Int
        var id2:Int
        var stringName:String
        var numSum:Int
        val context: Context = reminder_modify_image_back.getContext()
        num = Random.nextInt(0,3)
        numSum = num+1
        stringName = "note_transp$numSum"
        val id: Int = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
        reminder_modify_image_back.setImageResource(id)
        if (num == 0 || num == 1){
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin"+numSum+"_shadow"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin1_shad_modify.setImageResource(id2)
            }
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin"+numSum+"_shadow"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin2_shad_modify.setImageResource(id2)
            }
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin"+numSum+"_shadow"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin3_shad_modify.setImageResource(id2)
            }
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin"+numSum+"_shadow"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin4_shad_modify.setImageResource(id2)
            }
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin"+numSum+"_shadow"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin5_shad_modify.setImageResource(id2)
            }
        } else {
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin$numSum"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin1_shad_modify.setImageResource(id2)
            }
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin$numSum"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin2_shad_modify.setImageResource(id2)
            }
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin$numSum"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin3_shad_modify.setImageResource(id2)
            }
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin$numSum"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin4_shad_modify.setImageResource(id2)
            }
            num = Random.nextInt(0,2)
            if (num == 1){
                num = Random.nextInt(0,12)
                while(numbers.contains(num)){
                    num = Random.nextInt(0,12)
                }
                numbers.add(num)
                numSum = num+1
                stringName = "push_pin$numSum"
                id2 = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
                push_pin5_shad_modify.setImageResource(id2)
            }
        }
    }
}
