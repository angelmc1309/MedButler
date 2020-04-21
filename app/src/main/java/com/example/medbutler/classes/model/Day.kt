package com.example.medbutler.classes.model

class Day {
    private var date:String
    private val foodList :ArrayList<Food> = ArrayList<Food>(5)
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

}