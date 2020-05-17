package com.example.medbutler.classes.model

import java.io.Serializable

class Food: Serializable{
    private var name:String = ""
    private var foodDate:String = ""     // Mateix valor que l'atribut que dia al qual pertanyi, el guardem per si de cas el necessitéssim
    private var number:Int = -1
    private var nutricionalValues:ArrayList<String> = ArrayList()

    constructor(){
    }

    constructor(name:String, foodDate: String, number:Int){
        this.name = name
        this.foodDate = foodDate
        this.number = number
        nutricionalValues = ArrayList()
    }
    constructor(name: String, foodDate: String, number:Int, nutricional:ArrayList<String>){
        this.name = name
        this.foodDate = foodDate
        this.number = number
        nutricionalValues = nutricional.clone() as ArrayList<String>
    }

    fun getfoodDate():String{
        return foodDate
    }
    fun setfoodDate(foodDate:String){
        this.foodDate = foodDate
    }

    fun getnumber():Int{
        return number
    }
    fun setnumber(number:Int){
        this.number = number
    }

    override fun toString(): String {
        return this.name
    }

    fun getname(): String? {
        return this.name
    }
    fun setname(newName:String){
        this.name=newName
    }

    fun deleteFood(){
        this.name = ""
        this.foodDate = ""     // Mateix valor que l'atribut que dia al qual pertanyi, el guardem per si de cas el necessitéssim
        this.number = -1
        this.nutricionalValues = ArrayList()
    }

    fun getNutricionalValuesArray():ArrayList<String>{
        return nutricionalValues
    }

    fun setNutricionalValuesArray(nutricionalValues:ArrayList<String>){
        this.nutricionalValues=nutricionalValues
    }
}