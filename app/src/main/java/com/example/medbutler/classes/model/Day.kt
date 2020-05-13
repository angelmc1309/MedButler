package com.example.medbutler.classes.model

import java.io.Serializable

class Day: Serializable {
    private var date:String = ""
    private val foodList :ArrayList<Food> = ArrayList<Food>(5)
    private val reminderList:ArrayList<Reminder> = ArrayList<Reminder>()
    private val taskList:ArrayList<Task> = ArrayList<Task>()

    constructor(){
    }

    constructor(date:String){
        this.date = date
        for(x in 0 until 5){
            foodList.add(Food(""))
        }
    }

    fun getDate():String{
        return date
    }
    fun setFood(number:Int,name:String){
        val f = Food(name)
        foodList[number] = f
    }

    fun setDate(newDate:String){
        date = newDate
    }

    override fun toString(): String {
        return date
    }

    fun getFood():String{
        return foodList.toString()
    }

    fun getFoodArray():ArrayList<Food>{
        return foodList
    }

    fun getReminderArray():ArrayList<Reminder>{
        return reminderList
    }

    fun getTaskArray():ArrayList<Task>{
        return taskList
    }

    fun addReminder(id: String, reminderDate: String, reminderName: String, importance: Int,allowNotification:Boolean){

        val reminder = Reminder(id, reminderDate, reminderName, importance, allowNotification)
        reminderList.add(reminder)
    }
    fun addTask(id: String, taskDate: String, taskName: String, taskStartTimeMinute: Int,taskStartTimeHour:Int, allowNotification:Boolean){

        val task = Task(id, taskDate, taskName, taskStartTimeMinute,taskStartTimeHour, allowNotification)
        taskList.add(task)
    }
    fun removeReminder(id: String) {
        for(reminder in reminderList){
            if (id == reminder.id){
                reminderList.remove(reminder)
                return
            }
        }
    }
    fun removeTask(id: String) {
        for(task in taskList){
            if (id == task.id){
                taskList.remove(task)
                return
            }
        }
    }

}