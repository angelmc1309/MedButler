package com.example.medbutler.classes.model

class Task {
    private var taskDate:String = ""     // Mateix valor que l'atribut que dia al qual pertanyi, el guardem per si de cas el necessitéssim
    private var taskName:String = ""
    private var taskStartTimeMinute:Int = 0
    private var taskStartTimeHour:Int = 0

    constructor(){
    }

    constructor(taskDate: String, taskName:String,taskStartTimeMinute:Int,taskStartTimeHour:Int){
        this.taskDate = taskDate
        this.taskName = taskName
        this.taskStartTimeMinute = taskStartTimeMinute
        this.taskStartTimeHour = taskStartTimeHour
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
}