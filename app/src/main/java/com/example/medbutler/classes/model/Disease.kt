package com.example.medbutler.classes.model

import java.io.Serializable

class Disease(
    var id: String,
    private var name: String
):Serializable {

    override fun toString(): String {
        return this.name
    }
    fun setname(newName:String){
        this.name=newName
    }
}