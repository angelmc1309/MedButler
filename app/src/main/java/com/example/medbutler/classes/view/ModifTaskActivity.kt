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
import com.example.medbutler.classes.model.Task
import kotlinx.android.synthetic.main.activity_modif_task_layout.*
import kotlin.random.Random

class ModifTaskActivity : AppCompatActivity() {

    var startTimeMinuteTask: Int = -1
    var startTimeHourTask: Int = -1
    lateinit var extraObjectId:String

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modif_task_layout)
        val extraObject: Task = intent.extras!!.get("extra_object_reminder") as Task
        extraObjectId = extraObject.id

        taskNameModify.setText(extraObject.gettaskName())

        startTimeHourTask = extraObject.gettaskStartTimeHour()!!
        startTimeMinuteTask = extraObject.gettaskStartTimeMinute()!!
        val formattedHour = String.format("%02d", extraObject.gettaskStartTimeHour())
        val formattedMinute = String.format("%02d", extraObject.gettaskStartTimeMinute())
        startTimeClickTaskModify.setText(getString(R.string.time_select,formattedHour,formattedMinute))

        switchNotificationTaskModify.isChecked = extraObject.getallowNotification()!!

        startTimeClickTaskModify.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{view: TimePicker?, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                cal.set(Calendar.MINUTE,minute)
                startTimeHourTask = SimpleDateFormat("HH").format(cal.time).toInt()
                startTimeMinuteTask = SimpleDateFormat("mm").format(cal.time).toInt()
                startTimeClickTaskModify.text = SimpleDateFormat("    HH:mm").format(cal.time)
                startTimeClickTaskModify.error = null
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
        val backgroundLay: RelativeLayout = findViewById(R.id.backgroundModifyTask)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        val addBut: TextView = findViewById(R.id.btnModifyTask)
        val disBut: TextView = findViewById(R.id.btnDiscardModifyTask)

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

    fun actionModifyTask(view: View) {

        if(!taskNameModify.text.toString().isEmpty() && startTimeMinuteTask != -1 && startTimeHourTask != -1){

            //GET_CURRENT_DAY.getTaskListArray()?.find { it.id.equals(extraObjectId) }?.setreminderName(taskNameModify.text.toString())
            //GET_CURRENT_DAY.getTaskListArray()?.find { it.id.equals(extraObjectId) }?.settaskStartTimeHour(startTimeHourTask)
            //GET_CURRENT_DAY.getTaskListArray()?.find { it.id.equals(extraObjectId) }?.settaskStartTimeMinute(startTimeMinuteTask)
            //GET_CURRENT_DAY.getTaskListArray()?.find { it.id.equals(extraObjectId) }?.setallowNotification(switchNotificationTaskModify.isChecked)
            //GET_CURRENT_DAY.getTaskListArray()?.find { it.id.equals(extraObjectId) }?.id = taskNameModify.text.toString()
            //MainController.saveUserAll()

            val intent= Intent(this, DayActivity::class.java)
            startActivity(intent)
        } else if(taskNameModify.text.toString().isEmpty()){
            taskNameModify.error="Task's name empty!!"
            taskNameModify.requestFocus()
        } else if(startTimeMinuteTask == -1){
            startTimeClickTaskModify.error = "Time not selected!!"
            startTimeClickTaskModify.requestFocus()
        }else if (startTimeHourTask == -1){
            startTimeClickTaskModify.error = "Time not selected!!"
            startTimeClickTaskModify.requestFocus()
        }
    }

    fun actionDiscardModifyTask(view: View) {
        val intent= Intent(this, DayActivity::class.java)
        startActivity(intent)
    }

    fun createbackground(){
        val numbers = mutableListOf<Int>()
        var num:Int
        var id2:Int
        var stringName:String
        var numSum:Int
        val context: Context = task_modify_image_back.getContext()
        num = Random.nextInt(0,3)
        numSum = num+1
        stringName = "note_transp$numSum"
        val id: Int = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
        task_modify_image_back.setImageResource(id)
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
                push_pin1_norm_modify.setImageResource(id2)
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
                push_pin2_norm_modify.setImageResource(id2)
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
                push_pin3_norm_modify.setImageResource(id2)
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
                push_pin4_norm_modify.setImageResource(id2)
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
                push_pin5_norm_modify.setImageResource(id2)
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
                push_pin1_norm_modify.setImageResource(id2)
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
                push_pin2_norm_modify.setImageResource(id2)
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
                push_pin3_norm_modify.setImageResource(id2)
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
                push_pin4_norm_modify.setImageResource(id2)
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
                push_pin5_norm_modify.setImageResource(id2)
            }
        }
    }
}
