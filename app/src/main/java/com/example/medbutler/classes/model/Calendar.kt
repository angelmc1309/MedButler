package com.example.medbutler.classes.model

class Calendar {
    var list :ArrayList<Day> = ArrayList<Day>()
        get() = field
    constructor()

    fun addDay(date:String){
        val day = Day(date)
        list.add(day)
    }

    fun removeDay(day:String){
        for(date in list){
            if (date.getDate() == day){
                list.remove(date)
                return
            }
        }
    }

    fun setFood(date:String,order: Int, name: String) {
        find(date)?.setFood(order,name)
    }
    fun find(date: String): Day? {
        for(day in list){
            if(day.getDate() ==date){
                return day
            }
        }
        return null
    }

    fun existsThisDay(date: String): Boolean {
        return find(date) != null
    }

    override fun toString():String{
        return list.toString()
    }

    fun getFood(date:String): String? {
        if(find(date) != null) {
            return find(date)?.getFood()
        }else{
            return null
        }
    }

    fun getFoodArray(date:String): ArrayList<Food>? {
        if(find(date) != null) {
            return find(date)?.getFoodArray()
        }else{
            return null
        }
    }

    fun getReminderArray(date:String): ArrayList<Reminder>? {
        if(find(date) != null) {
            return find(date)?.getReminderArray()
        }else{
            return null
        }
    }

    fun getTaskArray(date:String): ArrayList<Task>? {
        if(find(date) != null) {
            return find(date)?.getTaskArray()
        }else{
            return null
        }
    }

    fun addReminder(id: String, reminderDate: String, reminderName: String, importance: Int,allowNotification:Boolean){
        find(reminderDate)?.addReminder(id,reminderDate,reminderName,importance,allowNotification)
    }
    fun addTask(id: String, taskDate: String, taskName: String, taskStartTimeMinute: Int,taskStartTimeHour:Int, allowNotification:Boolean){
        find(taskDate)?.addTask(id,taskDate,taskName,taskStartTimeMinute,taskStartTimeHour,allowNotification)
    }
    fun removeReminder(reminderDate: String, id: String) {
        find(reminderDate)?.removeReminder(id)
    }
    fun removeTask(taskDate: String, id: String) {
        find(taskDate)?.removeTask(id)
    }
}