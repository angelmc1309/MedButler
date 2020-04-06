package com.example.medbutler.classes.model

class Day {
    private var date:String
    constructor(date:String){
        this.date = date
    }

    fun getDate():String{
        return date
    }

    fun setDate(newDate:String){
        date = newDate
    }

}