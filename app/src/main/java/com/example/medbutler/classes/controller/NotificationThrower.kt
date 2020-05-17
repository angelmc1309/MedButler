package com.example.medbutler.classes.controller

import java.util.*

class NotificationThrower {
    val notI : NotificationInterface
    constructor(n:NotificationInterface){
        notI = n

    }
    fun updateTimeText(message: String){
        notI.updateTimeText(message)

    }
    fun startAlarm(c: Calendar){
        notI.startAlarm(c)
    }
    fun cancelAlarm(){
        notI.cancelAlarm()
    }
}