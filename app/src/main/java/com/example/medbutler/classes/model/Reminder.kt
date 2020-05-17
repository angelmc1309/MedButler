package com.example.medbutler.classes.model

import java.io.Serializable

class Reminder: Serializable {
    var id: String = ""
    private var reminderDate:String = ""     // Mateix valor que l'atribut que dia al qual pertanyi, el guardem per si de cas el necessitéssim
    private var reminderName:String = ""
    private var importance:Int = 1
    private var allowNotification:Boolean = false

    /*
    Importance -->  0 = indiferente/indifferent
                    1 = opcional/optional
                    2 = importante/important
                    3 = Muy importante/Very important
    */

    constructor(){
    }

    constructor(id: String, reminderDate: String, reminderName: String, importance:Int, allowNotification:Boolean){
        this.id = id
        this.reminderDate = reminderDate
        this.reminderName = reminderName
        this.importance = importance
        this.allowNotification = allowNotification
    }

    fun getreminderDate():String{
        return reminderDate
    }
    fun setreminderDate(reminderDate:String){
        this.reminderDate = reminderDate
    }

    fun getreminderName():String{
        return reminderName
    }
    fun setreminderName(reminderName:String){
        this.reminderName = reminderName
    }
    fun getimportance():Int{
        return importance
    }
    fun setimportance(importance:Int){
        this.importance = importance
    }
    fun getallowNotification(): Boolean? {
        return this.allowNotification
    }
    fun setallowNotification(allowNotification:Boolean){
        this.allowNotification = allowNotification
    }
}