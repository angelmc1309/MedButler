package com.example.medbutler.classes.Model

open class Usuari {
    private lateinit var username:String
    private lateinit var passwrd:String
    private lateinit var birthday:String
    private lateinit var gender:String
    constructor(username:String,password:String, birthday:String, gender:String){
        this.username=username
        this.passwrd=password
        this.birthday=birthday
        this.gender=gender
    }

    //var d=Usuari("k","j","s","s")

}