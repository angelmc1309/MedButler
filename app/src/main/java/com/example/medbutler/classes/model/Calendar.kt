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

}