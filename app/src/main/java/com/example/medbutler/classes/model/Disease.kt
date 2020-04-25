package com.example.medbutler.classes.model

import java.io.Serializable

class Disease:Serializable {
    var id: String
     private var name: String
    constructor(){
        id=""
        name=""
    }
    constructor(id:String, name:String){
        this.id=id
        this.name=name
    }


    override fun toString(): String {
        return this.name
    }
    fun setname(newName:String){
        this.name=newName
    }
}
