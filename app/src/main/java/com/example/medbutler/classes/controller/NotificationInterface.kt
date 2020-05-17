package com.example.medbutler.classes.controller

import java.util.*

interface NotificationInterface {
    fun updateTimeText(message: String)
    fun startAlarm(c: Calendar)
    fun cancelAlarm()
}