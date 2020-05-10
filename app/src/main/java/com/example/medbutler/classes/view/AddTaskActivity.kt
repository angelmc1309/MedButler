package com.example.medbutler.classes.view

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
import com.example.medbutler.R
import java.text.SimpleDateFormat
import java.util.Calendar
import com.example.medbutler.classes.controller.*
import kotlinx.android.synthetic.main.activity_add_task_layout.*
import kotlin.random.Random

class AddTaskActivity : AppCompatActivity() {

    var startTimeMinuteTask: Int = -1
    var startTimeHourTask: Int = -1

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task_layout)
        startTimeClickTask.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{view: TimePicker?, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                cal.set(Calendar.MINUTE,minute)
                startTimeHourTask = SimpleDateFormat("HH").format(cal.time).toInt()
                startTimeMinuteTask = SimpleDateFormat("mm").format(cal.time).toInt()
                startTimeClickTask.text = SimpleDateFormat("    HH:mm").format(cal.time)
                startTimeClickTask.error = null
            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
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
        val backgroundLay: RelativeLayout = findViewById(R.id.backgroundAddTask)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        val addBut: TextView = findViewById(R.id.btnAddTask)
        val disBut: TextView = findViewById(R.id.btnDiscardTask)

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

    fun addTask(view: View) {

        if(!taskName.text.toString().isEmpty() && startTimeMinuteTask != -1 && startTimeHourTask != -1){
            //addTask(taskName.text.toString(),taskName.text.toString(),startTimeMinuteTask,startTimeHourTask,switchNotificationTask.isChecked)
            //MainController.saveUserAll()
            val intent= Intent(this, DayActivity::class.java)
            startActivity(intent)
        } else if(taskName.text.toString().isEmpty()){
            taskName.error="Task's name empty!!"
            taskName.requestFocus()
        } else if(startTimeMinuteTask == -1){
            startTimeClickTask.error = "Time not selected!!"
            startTimeClickTask.requestFocus()
        }else if (startTimeHourTask == -1){
            startTimeClickTask.error = "Time not selected!!"
            startTimeClickTask.requestFocus()
        }
    }

    fun discardTask(view: View) {
        val intent= Intent(this, DayActivity::class.java)
        startActivity(intent)
    }

    fun createbackground(){
        val numbers = mutableListOf<Int>()
        var num:Int
        var id2:Int
        var stringName:String
        var numSum:Int
        val context: Context = task_image_back.getContext()
        num = Random.nextInt(0,3)
        numSum = num+1
        stringName = "note_transp$numSum"
        val id: Int = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
        task_image_back.setImageResource(id)
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
                push_pin1_norm.setImageResource(id2)
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
                push_pin2_norm.setImageResource(id2)
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
                push_pin3_norm.setImageResource(id2)
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
                push_pin4_norm.setImageResource(id2)
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
                push_pin5_norm.setImageResource(id2)
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
                push_pin1_norm.setImageResource(id2)
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
                push_pin2_norm.setImageResource(id2)
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
                push_pin3_norm.setImageResource(id2)
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
                push_pin4_norm.setImageResource(id2)
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
                push_pin5_norm.setImageResource(id2)
            }
        }
    }
}
