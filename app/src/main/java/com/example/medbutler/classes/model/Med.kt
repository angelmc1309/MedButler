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

    fun toStringAllAtributes(): String {
        var hour:String = ('0' + startTimeHour.toString())
        var minute:String = ('0' + startTimeMinute.toString())
        var string: String = name + " - every " + period + " hours - for "+ duration + " days - " + hour.substring(hour.length-2) + ":" + minute.substring(minute.length-2)
        if (allowNotification){
            return (string + " - notification ON")
        }else{
            return (string + " - notification OFF")
        }
    }


}