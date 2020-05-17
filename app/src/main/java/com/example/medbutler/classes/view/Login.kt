package com.example.medbutler.classes.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController
import kotlinx.android.synthetic.main.activity_login.*
import android.app.AlarmManager
import android.app.PendingIntent

import android.content.Context
import android.media.MediaSession2
import android.os.Message


import android.widget.TimePicker
import com.example.medbutler.classes.controller.NotificationInterface
import com.example.medbutler.classes.controller.NotificationThrower
import java.text.DateFormat
import java.util.Calendar
class Login : AppCompatActivity(),NotificationInterface  {





    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login: TextView = findViewById(R.id.butLogin)
        val register: TextView = findViewById(R.id.newUserLabelLogin)

        login.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.grey_clear)))
        login.setBackgroundResource(R.drawable.rounded_button)
        //addBut.setTextColor()

        register.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.grey_transp)))
        register.setBackgroundResource(R.drawable.rounded_button)
        //addBut.setTextColor()
        val n : NotificationThrower= NotificationThrower(this)
        MainController.setNotificationThrower(n)

    }

    fun actionLog(view: View){
        if(usernameLogin.text.toString().isEmpty()){
            usernameLogin.error="Username empty!!"
            usernameLogin.requestFocus()
            return
        }
        if(passwordLogin.text.toString().isEmpty()){
            passwordLogin.error="Password empty!"
            usernameLogin.requestFocus()
            return
        }
        if (!usernameLogin.text.toString().isEmpty() && !passwordLogin.text.toString().isEmpty()){
            MainController.login(this,usernameLogin.text.toString(),passwordLogin.text.toString())
        }

    }
    fun actionRegistre(view:View){
        val intent=Intent(this, Sign_up::class.java)
        startActivity(intent)
    }





     override fun updateTimeText(message: String) {
         MainController.notMessage = "Alarm set for: "

         MainController.notMessage += message

    }

     override fun startAlarm(c: Calendar) {
        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlertReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0)
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
    }

     override fun cancelAlarm() {
        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlertReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0)
        alarmManager.cancel(pendingIntent)

    }


}

