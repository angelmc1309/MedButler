package com.example.medbutler.classes.model

object Calendar {
    var list :ArrayList<Day> = ArrayList<Day>()
        get() = field

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

}