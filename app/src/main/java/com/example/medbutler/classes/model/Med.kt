package com.example.medbutler.classes.model

import java.time.chrono.ChronoPeriod

class Med(
     var id: String,
    private var name: String,
    private var period: Int,
    private var duration: Int,
    private var startTimeMinute:Int,
    private var startTimeHour:Int,
    private var allowNotification:Boolean
) {

    override fun toString(): String {
        return name
    }


}