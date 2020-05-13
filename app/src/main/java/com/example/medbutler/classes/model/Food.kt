package com.example.medbutler.classes.model

import java.io.Serializable

class Food: Serializable{
    private var name:String = ""
    private var nutricionalValues:ArrayList<String> = ArrayList()

    constructor(){
    }

    constructor(name:String){
        this.name = name
        nutricionalValues = ArrayList()
    }
    constructor(name: String,nutricional:ArrayList<String>){
        this.name = name
        nutricionalValues = nutricional.clone() as ArrayList<String>
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

    fun getNutricionalValuesArray():ArrayList<String>{
        return nutricionalValues
    }
}