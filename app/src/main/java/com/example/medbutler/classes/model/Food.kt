package com.example.medbutler.classes.model

class Food {
    private var name:String
    private val nutricionalValues:ArrayList<String>
    constructor(name:String){
        this.name = name
        nutricionalValues = ArrayList()
    }
    constructor(name: String,nutricional:ArrayList<String>){
        this.name = name
        nutricionalValues = nutricional.clone() as ArrayList<String>
    }
}