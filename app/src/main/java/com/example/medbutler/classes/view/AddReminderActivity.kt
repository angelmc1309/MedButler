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
import kotlinx.android.synthetic.main.activity_add_reminder_layout.*
import kotlin.random.Random

class AddReminderActivity : AppCompatActivity() {

    lateinit var optionImportance: Spinner
    var resultImportance:Int = -1

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder_layout)
        optionImportance = findViewById(R.id.spinnerImportance) as Spinner

        /*
        Importance -->  0 = indiferente/indifferent
                        1 = opcional/optional
                        2 = importante/important
                        3 = Muy importante/Very important
        */

        val optionsImportance = arrayOf("indifferent", "optional", "important", "very important")
        optionImportance.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsImportance)
        optionImportance.setSelection(1)

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
        val backgroundLay: RelativeLayout = findViewById(R.id.backgroundAddReminder)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        val addBut: TextView = findViewById(R.id.btnAddReminder)
        val disBut: TextView = findViewById(R.id.btnDiscardReminder)

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

    fun addReminder(view: View) {

        if(!reminderName.text.toString().isEmpty() && resultImportance != -1){
            //addReminder(reminderName.text.toString(),reminderName.text.toString(),resultImportance,switchNotificationReminder.isChecked)
            //MainController.saveUserAll()
            val intent= Intent(this, DayActivity::class.java)
            startActivity(intent)
        } else if(reminderName.text.toString().isEmpty()){
            reminderName.error="Reminder's name empty!!"
            reminderName.requestFocus()
        }
    }

    fun discardReminder(view: View) {
        val intent= Intent(this, DayActivity::class.java)
        startActivity(intent)
    }

    fun createbackground(){
        val numbers = mutableListOf<Int>()
        var num:Int
        var id2:Int
        var stringName:String
        var numSum:Int
        val context: Context = reminder_image_back.getContext()
        num = Random.nextInt(0,3)
        numSum = num+1
        stringName = "note_transp$numSum"
        val id: Int = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
        reminder_image_back.setImageResource(id)
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
                push_pin1_shad.setImageResource(id2)
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
                push_pin2_shad.setImageResource(id2)
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
                push_pin3_shad.setImageResource(id2)
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
                push_pin4_shad.setImageResource(id2)
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
                push_pin5_shad.setImageResource(id2)
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
                push_pin1_shad.setImageResource(id2)
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
                push_pin2_shad.setImageResource(id2)
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
                push_pin3_shad.setImageResource(id2)
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
                push_pin4_shad.setImageResource(id2)
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
                push_pin5_shad.setImageResource(id2)
            }
        }
    }
}
