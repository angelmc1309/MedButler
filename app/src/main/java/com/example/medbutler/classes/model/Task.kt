package com.example.medbutler.classes.model

import java.io.Serializable

class Task: Serializable {
    var id: String = ""
    private var taskDate:String = ""     // Mateix valor que l'atribut que dia al qual pertanyi, el guardem per si de cas el necessitéssim
    private var taskName:String = ""
    private var taskStartTimeMinute:Int = 0
    private var taskStartTimeHour:Int = 0
    private var allowNotification:Boolean = false

    constructor(){
    }

    constructor(id: String, taskDate: String, taskName:String,taskStartTimeMinute:Int,taskStartTimeHour:Int, allowNotification:Boolean){
        this.id = id
        this.taskDate = taskDate
        this.taskName = taskName
        this.taskStartTimeMinute = taskStartTimeMinute
        this.taskStartTimeHour = taskStartTimeHour
        this.allowNotification = allowNotification
    }

    fun gettaskName():String{
        return taskName
    }
    fun settaskName(taskName:String){
        this.taskName = taskName
    }
    fun gettaskStartTimeMinute(): Int? {
        return this.taskStartTimeMinute
    }
    fun settaskStartTimeMinute(newtaskStartTimeMinute:Int){
        this.taskStartTimeMinute = newtaskStartTimeMinute
    }
    fun gettaskStartTimeHour(): Int? {
        return this.taskStartTimeHour
    }
    fun settaskStartTimeHour(newtaskStartTimeHour:Int){
        this.taskStartTimeHour = newtaskStartTimeHour
    }
    fun gettaskDate():String{
        return taskDate
    }
    fun settaskDate(newtaskDate:String){
        taskDate = newtaskDate
    }
    fun getallowNotification(): Boolean? {
        return this.allowNotification
    }
    fun setallowNotification(allowNotification:Boolean){
        this.allowNotification = allowNotification
    }
}