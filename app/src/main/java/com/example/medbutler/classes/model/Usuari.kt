package com.example.medbutler.classes.model

open class Usuari {
    private var username:String
    private var passwrd:String
    private var birthday:String
    private var gender:String
    private val medList:MedList = MedList()
    private val calendar:Calendar= Calendar()
    constructor(username:String,password:String, birthday:String, gender:String){
        this.username=username
        this.passwrd=password
        this.birthday=birthday
        this.gender=gender
    }

    fun getId():String{
        return "test"
    }

    fun addMed(id: String,name: String, period: Int,duration: Int,startTimeMinute:Int,
               startTimeHour:Int,allowNotification:Boolean){
        medList.addMed(id, name, period, duration, startTimeMinute, startTimeHour, allowNotification)
    }

    fun getMedList():String {
        return medList.toString()
    }

    fun removeMed(id: String) {
        medList.removeMed(id)
    }
    fun setFood(date: String,order:Int,name: String){
        calendar.setFood(date,order,name)
    }

    fun existsThisDay(date: String): Boolean {
        return calendar.existsThisDay(date)
    }

    fun addDay(date: String){
        calendar.addDay(date)
    }

    fun getDayList():String{
        return calendar.toString()
    }

    fun removeDay(date: String){
        calendar.removeDay(date)
    }
    //var d=Usuari("k","j","s","s")


}