package com.example.medbutler.classes.model

import java.io.Serializable

class Med(
    var id: String,
    private var name: String,
    private var period: Int,
    private var duration: Int,
    private var startTimeMinute:Int,
    private var startTimeHour:Int,
    private var allowNotification:Boolean
):Serializable {

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

    fun getname(): String? {
        return this.name
    }
    fun setname(newName:String){
        this.name=newName
    }
    fun getperiod(): Int? {
        return this.period
    }
    fun setperiod(newPeriod:Int){
        this.period=newPeriod
    }
    fun getduration(): Int? {
        return this.duration
    }
    fun setduration(newduration:Int){
        this.duration=newduration
    }
    fun getstartTimeMinute(): Int? {
        return this.startTimeMinute
    }
    fun setstartTimeMinute(newstartTimeMinute:Int){
        this.startTimeMinute=newstartTimeMinute
    }
    fun getstartTimeHour(): Int? {
        return this.startTimeHour
    }
    fun setstartTimeHour(newstartTimeHour:Int){
        this.startTimeHour=newstartTimeHour
    }
    fun getallowNotification(): Boolean? {
        return this.allowNotification
    }
    fun setallowNotification(allowNotification:Boolean){
        this.allowNotification=allowNotification
    }
}