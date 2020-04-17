package com.example.medbutler.classes.model

class Calendar {
    var list :ArrayList<Day> = ArrayList<Day>()

    fun addDay(date:String){
        val day = Day(date)
        list.add(day)
    }

    fun removeDay(day:String){
        for(date in list){
            if (day == date.getDate()){
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

}