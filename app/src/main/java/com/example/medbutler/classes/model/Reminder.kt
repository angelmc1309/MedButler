package com.example.medbutler.classes.model

class Reminder {
    private var reminderName:String
    private var importance:Int

    /*
    Importance -->  0 = indiferente/indifferent
                    1 = opcional/optional
                    2 = importante/important
                    3 = Muy importante/Very important
    */

    constructor(){
        reminderName = ""
        importance = 0
    }

    /* No s'hauria d'utilitzar

    constructor(reminderName:String){
        this.reminderName = reminderName
        importance = 0
    }
     */

    constructor(reminderName: String, importance:Int){
        this.reminderName = reminderName
        this.importance = importance
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
}